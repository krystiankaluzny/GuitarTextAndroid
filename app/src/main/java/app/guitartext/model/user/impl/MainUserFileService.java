package app.guitartext.model.user.impl;

import android.support.annotation.NonNull;

import com.j256.ormlite.dao.Dao;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.db.schema.tables.BaseFile;
import app.guitartext.db.schema.tables.OpenedFile;
import app.guitartext.db.schema.tables.User;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.user.UserFileService;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

public class MainUserFileService implements UserFileService
{
	private static final Logger logger = LoggerManager.getLogger();

	public static final char SEPARATOR = '/';

	private User currentUser;
	private Dao<BaseFile, Integer> baseFileDao;
	private Dao<OpenedFile, Integer> openedFileDao;

	private List<FileInfo> baseList = null;
	private List<FileInfo> favouriteList = null;
	private List<FileInfo> recentList = null;

	@Inject
	public MainUserFileService(User currentUser, Dao<BaseFile, Integer> baseFileDao, Dao<OpenedFile, Integer> openedFileDao)
	{
		this.currentUser = currentUser;
		this.baseFileDao = baseFileDao;
		this.openedFileDao = openedFileDao;
	}

	@NonNull
	@Override
	public List<FileInfo> getBaseFiles()
	{
		if(baseList == null) getBaseFileList();
		return baseList;
	}

	@NonNull
	@Override
	public List<FileInfo> getFavouriteFiles()
	{
		if(favouriteList == null) getFavouriteFileList();
		return favouriteList;
	}

	@NonNull
	@Override
	public List<FileInfo> getRecentOpenedFiles()
	{
		if(recentList == null) getRecentOpenedFileList();
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

	private void getBaseFileList()
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
			FileInfo i = new FileInfo(
					baseFile.getId(),
					baseFile.isDirectory(),
					baseFile.getPath(),
					getNameForPath(baseFile.getPath()));

			baseList.add(i);
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

	private void getFavouriteFileList()
	{
		favouriteList = new ArrayList<>();
	}

	private void getRecentOpenedFileList()
	{
		recentList = new ArrayList<>();
	}

	private String getNameForPath(String path)
	{
		int index = path.lastIndexOf(SEPARATOR);
		if(index < 0) return path;
		if(path.length() == 1) return path;
		return path.substring(index + 1);
	}
}
