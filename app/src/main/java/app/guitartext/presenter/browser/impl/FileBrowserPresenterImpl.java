package app.guitartext.presenter.browser.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import app.guitartext.model.fileInfo.FileInfoManager;
import app.guitartext.ui.browser.PathLayout;
import app.guitartext.presenter.browser.FileBrowserPresenter;
import app.guitartext.presenter.browser.PathItem;
import app.guitartext.ui.text.TextActivity;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserPresenterImpl implements FileBrowserPresenter, PathLayout.OnPathItemClickedListener
{
	private static final FileInfo ROOT_LOCATION = new FileInfo(0, true, "/", "/");
	private final Activity activity;
	private final FileInfo startFileLocation;
	private final FileInfoManager fileInfoManager;
	private final FileBrowserPresenter.View view;

	private FileInfo currentLocation;
	private List<FileInfo> currentLocationContent = Collections.emptyList();
	private List<PathItem> currentLocationChain = Collections.emptyList();

	@Inject
	public FileBrowserPresenterImpl(FileBrowserPresenter.View view, Activity activity, FileInfo startFileLocation, FileInfoManager fileInfoManager)
	{
		this.view = view;
		this.activity = activity;
		this.startFileLocation = startFileLocation;
		this.fileInfoManager = fileInfoManager;
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

		view.pathChanged(currentLocationChain, currentLocationContent);
	}

	private void browseLocation(FileInfo startLocation)
	{
		currentLocation = fileInfoManager.getValidateDirectory(startLocation);
		currentLocationContent = fileInfoManager.getLocationContent(currentLocation);
	}

	private void updatePathChain()
	{
		List<FileInfo> fileInfoList = fileInfoManager.getFileWithAncestors(currentLocation);

		currentLocationChain = Lists.transform(fileInfoList, input -> new PathItem(input.getName(), input));
	}

}