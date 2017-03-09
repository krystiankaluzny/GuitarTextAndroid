package app.guitartext.ui.browser.presenter.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.guitartext.R;
import app.guitartext.ui.ListEntry;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;
import app.guitartext.ui.tekst.TextActivity;
import app.guitartext.user.fileInfo.FileInfo;
import app.guitartext.user.fileInfo.ParcelableFileInfoWrapper;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileBrowserPresenterImpl implements FileBrowserPresenter
{
	private static final FileInfo ROOT_LOCATION = new FileInfo(0, true, "/", "/");
	private final Activity activity;
	private final FileInfo startFileLocation;
	private FileInfo currentLocation;

	private List<FileListEntry> currentEntryList = new ArrayList<>();

	public FileBrowserPresenterImpl(Activity activity, FileInfo startFileLocation)
	{
		this.activity = activity;
		this.startFileLocation = startFileLocation;

		browseLocation(this.startFileLocation);
	}

	@Override
	public int getFileCount()
	{
		return currentEntryList.size();
	}

	@Override
	public ListEntry getFileEntry(int filePosition)
	{
		return getFileListEntry(filePosition);
	}

	@Override
	public void fileSelected(int filePosition)
	{
		FileListEntry fileListEntry = getFileListEntry(filePosition);
		if(fileListEntry == null)
		{
			browseLocation(ROOT_LOCATION);
		}
		else if(fileListEntry.getFileInfo().isDirectory())
		{
			browseLocation(fileListEntry.getFileInfo());
		}
		else
		{
			Intent intent = new Intent(activity, TextActivity.class);
			intent.putExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO, ParcelableFileInfoWrapper.wrap(fileListEntry.getFileInfo()));
			activity.startActivity(intent);
		}
	}

	@Nullable
	private FileListEntry getFileListEntry(int position)
	{
		if(position < 0 || position >= currentEntryList.size()) return null;
		return currentEntryList.get(position);
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

		File[] subFiles = file.listFiles();

		if(subFiles == null || subFiles.length == 0)
		{
			currentEntryList = Collections.emptyList();
			return;
		}

		currentEntryList = new ArrayList<>(subFiles.length);
		int i = 0;
		for(File subFile : subFiles)
		{
			FileInfo fileInfo = new FileInfo(i, subFile.isDirectory(), subFile.getAbsolutePath(), subFile.getName());
			int resourceId = fileInfo.isDirectory() ? R.drawable.folder_base : R.drawable.file_base;
			currentEntryList.add(new FileListEntry(fileInfo, resourceId));
			i++;
		}
	}
}
