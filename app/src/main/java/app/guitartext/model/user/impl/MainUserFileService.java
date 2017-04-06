package app.guitartext.model.user.impl;

import android.support.annotation.NonNull;

import com.j256.ormlite.dao.Dao;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.db.schema.tables.BaseFile;
import app.guitartext.db.schema.tables.OpenedFile;
import app.guitartext.db.schema.tables.User;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.FileInfoService;
import app.guitartext.model.user.UserFileService;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

public class MainUserFileService implements UserFileService
{
	private static final Logger logger = LoggerManager.getLogger();

	private final User currentUser;
	private final Dao<BaseFile, Integer> baseFileDao;
	private final Dao<OpenedFile, Integer> openedFileDao;
	private final FileInfoService fileInfoService;

	private List<FileInfo> baseList = null;
	private List<FileInfo> favouriteList = null;
	private List<FileInfo> recentList = null;

	@Inject
	public MainUserFileService(User currentUser, Dao<BaseFile, Integer> baseFileDao, Dao<OpenedFile, Integer> openedFileDao, FileInfoService fileInfoService)
	{
		this.currentUser = currentUser;
		this.baseFileDao = baseFileDao;
		this.openedFileDao = openedFileDao;
		this.fileInfoService = fileInfoService;
	}

	@NonNull
	@Override
	public List<FileInfo> getBaseFiles()
	{
		if(baseList == null) loadBaseFileList();
		return baseList;
	}

	@NonNull
	@Override
	public List<FileInfo> getFavouriteFiles()
	{
		if(favouriteList == null) loadFavouriteFileList();
		return favouriteList;
	}

	@NonNull
	@Override
	public List<FileInfo> getRecentOpenedFiles()
	{
		if(recentList == null) loadRecentOpenedFileList();
		return recentList;
	}

	@Override
	public void addBase(FileInfo fileInfo)
	{
		if(fileInfo == null)
		{
			logger.e("fileInfo is null");
			return;
		}
		BaseFile baseFile = new BaseFile(currentUser, fileInfo.getPath(), fileInfo.isDirectory());

		try
		{
			baseFileDao.create(baseFile);
			baseList.add(fileInfo);
		} catch(SQLException e)
		{
			logger.e(e);
		}
	}

	@Override
	public void fileOpened(FileInfo fileInfo)
	{
		List<OpenedFile> openedFiles = new ArrayList<>();
		try
		{
			openedFiles = openedFileDao.queryForEq(OpenedFile.PATH, fileInfo.getPath());
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		if(openedFiles.isEmpty())
		{
			createOpenedFile(fileInfo);
		}
		else
		{
			updateOpenedFile(openedFiles.get(0));
		}

		loadFavouriteFileList();
		loadRecentOpenedFileList();
	}

	private void createOpenedFile(FileInfo fileInfo)
	{
		OpenedFile openedFile = new OpenedFile(currentUser,
				fileInfo.getPath(),
				currentUser.getPreferences().getDefaultCharset(),
				1,
				(int) (System.currentTimeMillis() / 1000));

		try
		{
			openedFileDao.create(openedFile);
		} catch(SQLException e)
		{
			logger.e(e);
		}
	}

	private void updateOpenedFile(OpenedFile openedFile)
	{
		openedFile.setOpenCount(openedFile.getOpenCount() + 1);
		openedFile.setLastOpenTimestamp((int) (System.currentTimeMillis() / 1000));

		try
		{
			openedFileDao.update(openedFile);
		} catch(SQLException e)
		{
			logger.e(e);
		}
	}

	private void loadBaseFileList()
	{
		List<BaseFile> baseFiles = new ArrayList<>();
		try
		{
			baseFiles = baseFileDao.queryForEq(User.FOREIGN_ID, currentUser);
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		baseList = new ArrayList<>(baseFiles.size());

		for(BaseFile baseFile : baseFiles)
		{
			fileInfoService.createFileFromPath(baseFile.getPath())
					.ifPresent(fileInfo -> baseList.add(fileInfo));
		}

		if(baseList.isEmpty())
		{
			baseList.add(new FileInfo(1, true, "/", "/"));

			try
			{
				baseFileDao.create(new BaseFile(currentUser, "/", true));
			} catch(SQLException e)
			{
				logger.e(e);
			}
		}
	}

	private void loadFavouriteFileList()
	{
		favouriteList = loadFromOpenedFileDao(OpenedFile.OPEN_COUNT,
				currentUser.getPreferences().getMaxFavouriteCount());
	}

	private void loadRecentOpenedFileList()
	{
		recentList = loadFromOpenedFileDao(OpenedFile.LAST_OPEN_TIMESTAMP,
				currentUser.getPreferences().getMaxRecentCount());
	}

	private List<FileInfo> loadFromOpenedFileDao(String orderBy, int limit)
	{
		try
		{
			List<OpenedFile> recentOpened = openedFileDao.queryBuilder()
					.orderBy(orderBy, false)
					.limit((long) limit)
					.query();

			List<FileInfo> result = new ArrayList<>(recentOpened.size());

			for(OpenedFile fav : recentOpened)
			{
				fileInfoService.createFileFromPath(fav.getPath())
						.ifPresent(result::add);
			}

			return result;

		} catch(SQLException e)
		{
			logger.e(e);
		}

		return Collections.emptyList();
	}
}
