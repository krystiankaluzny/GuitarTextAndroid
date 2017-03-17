package app.guitartext.presenter.browser.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.common.collect.Lists;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.FileInfoService;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;
import app.guitartext.model.user.UserFileService;
import app.guitartext.presenter.browser.FileBrowserPresenter;
import app.guitartext.presenter.browser.PathItem;
import app.guitartext.ui.browser.PathLayout;
import app.guitartext.ui.text.TextActivity;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserPresenterImpl implements FileBrowserPresenter, PathLayout.OnPathItemClickedListener
{
	private static final Logger logger = LoggerManager.getLogger();

	private static final FileInfo ROOT_LOCATION = new FileInfo(0, true, "/", "/");
	private static final FileInfoComparator FILE_INFO_COMPARATOR = new FileInfoComparator();

	private final Activity activity;
	private final FileInfo startFileLocation;
	private final FileInfoService fileInfoService;
	private final UserFileService userFileService;
	private final FileBrowserPresenter.View view;

	private FileInfo currentLocation;
	private List<FileInfo> currentLocationContent = Collections.emptyList();
	private List<PathItem> currentLocationChain = Collections.emptyList();

	@Inject
	public FileBrowserPresenterImpl(FileBrowserPresenter.View view, Activity activity, FileInfo startFileLocation, FileInfoService fileInfoService, UserFileService userFileService)
	{
		this.view = view;
		this.activity = activity;
		this.startFileLocation = startFileLocation;
		this.fileInfoService = fileInfoService;
		this.userFileService = userFileService;
	}

	@Override
	public void fileSelected(int filePosition)
	{
		fileSelected(getFileListEntry(filePosition));
	}

	@Override
	public void fileSelected(FileInfo fileInfo)
	{
		if(fileInfo == null)
		{
			updateLocation(ROOT_LOCATION);
		}
		else if(fileInfo.isDirectory())
		{
			updateLocation(fileInfo);
		}
		else
		{
			Intent intent = new Intent(activity, TextActivity.class);
			intent.putExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO, ParcelableFileInfoWrapper.wrap(fileInfo));
			activity.startActivity(intent);
		}
	}

	@Override
	public void addBaseFile(int filePosition)
	{
		FileInfo toBase = getFileListEntry(filePosition);
		if(toBase != null)
		{
			userFileService.addBase(toBase);
		}
	}

	@Override
	public void onPathItemClicked(int position, PathItem pathItem)
	{
		browseLocation((FileInfo) pathItem.getTag());
		updatePathChain();
	}

	@Nullable
	private FileInfo getFileListEntry(int position)
	{
		if(position < 0 || position >= currentLocationContent.size()) return null;
		return currentLocationContent.get(position);
	}

	private FileInfo createFileInfo(int position, File file)
	{
		if(file == null) return new FileInfo(position, false, "", "");
		return new FileInfo(position, file.isDirectory(), file.getAbsolutePath(), file.getName());
	}

	private void updateLocation(FileInfo location)
	{
		if((currentLocation != null && location != null) &&
				(Objects.equals(currentLocation, location) || Objects.equals(currentLocation.getPath(), location.getPath())))
			return;

		browseLocation(location);
		updatePathChain();

		view.onPathChanged(currentLocationChain, currentLocationContent);
	}

	private void browseLocation(FileInfo startLocation)
	{
		currentLocation = fileInfoService.getValidateDirectory(startLocation);
		currentLocationContent = fileInfoService.getLocationContent(currentLocation);
		Collections.sort(currentLocationContent, FILE_INFO_COMPARATOR);
	}

	private void updatePathChain()
	{
		List<FileInfo> fileInfoList = fileInfoService.getFileWithAncestors(currentLocation);

		currentLocationChain = Lists.transform(fileInfoList, input -> new PathItem(input.getName(), input));
	}

	private static class FileInfoComparator implements Comparator<FileInfo>
	{
		@Override
		public int compare(FileInfo o1, FileInfo o2)
		{
			if(o1.isDirectory() && !o2.isDirectory()) return -1;
			if(!o1.isDirectory() && o2.isDirectory()) return 1;
			return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
		}
	}

}
