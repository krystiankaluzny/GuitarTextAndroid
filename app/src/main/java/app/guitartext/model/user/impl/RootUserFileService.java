package app.guitartext.model.user.impl;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.user.UserFileService;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class RootUserFileService implements UserFileService
{
	@NonNull
	@Override
	public List<FileInfo> getBaseFiles()
	{
		return Collections.singletonList(new FileInfo(1, true, "/", "/"));
	}

	@NonNull
	@Override
	public List<FileInfo> getFavouriteFiles()
	{
		return Collections.emptyList();
	}

	@NonNull
	@Override
	public List<FileInfo> getRecentOpenedFiles()
	{
		return Collections.emptyList();
	}

	@Override
	public void addBase(FileInfo fileInfo)
	{

	}
}
