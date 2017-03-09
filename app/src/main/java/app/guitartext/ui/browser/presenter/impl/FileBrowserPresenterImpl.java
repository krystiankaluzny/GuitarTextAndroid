package app.guitartext.ui.browser.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import app.guitartext.ui.browser.PathLayout;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;
import app.guitartext.ui.browser.presenter.PathItem;
import app.guitartext.ui.tekst.TextActivity;
import app.guitartext.user.fileInfo.FileInfo;
import app.guitartext.user.fileInfo.ParcelableFileInfoWrapper;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserPresenterImpl implements FileBrowserPresenter, PathLayout.OnPathItemClickedListener
{
	private static final FileInfo ROOT_LOCATION = new FileInfo(0, true, "/", "/");
	private final Activity activity;
	private final FileInfo startFileLocation;
	private final FileBrowserPresenter.View view;

	private FileInfo currentLocation;
	private List<FileInfo> currentLocationContent = Collections.emptyList();
	private List<PathItem> currentLocationChain = Collections.emptyList();

	@Inject
	public FileBrowserPresenterImpl(FileBrowserPresenter.View view, Activity activity, FileInfo startFileLocation)
	{
		this.view = view;
		this.activity = activity;
		this.startFileLocation = startFileLocation;
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
		if(startLocation == null) startLocation = ROOT_LOCATION;

		File file = new File(startLocation.getPath());
		if(!file.exists())
		{
			file = new File(ROOT_LOCATION.getPath());
			currentLocation = ROOT_LOCATION;
		}

		if(!file.isDirectory())
		{
			file = new File(file.getParent());
			currentLocation = new FileInfo(0, true, file.getAbsolutePath(), file.getName());
		}
		else
		{
			currentLocation = startLocation;
		}

		File[] subFiles = file.listFiles();

		if(subFiles == null || subFiles.length == 0)
		{
			currentLocationContent = Collections.emptyList();
			return;
		}

		currentLocationContent = new ArrayList<>(subFiles.length);
		int i = 0;
		for(File subFile : subFiles)
		{
			currentLocationContent.add(createFileInfo(i, subFile));
			i++;
		}
	}

	private void updatePathChain()
	{
		LinkedList<PathItem> pathItemList = new LinkedList<>();

		File file = new File(currentLocation.getPath());
		FileInfo fileInfo = createFileInfo(0, file);

		while(file != null && !TextUtils.isEmpty(fileInfo.getName()))
		{
			pathItemList.addFirst(new PathItem(fileInfo.getName(), fileInfo));
			file = file.getParentFile();
			fileInfo = createFileInfo(0, file);
		}

		pathItemList.addFirst(new PathItem(ROOT_LOCATION.getName(), ROOT_LOCATION));
		currentLocationChain = pathItemList;
	}

}
