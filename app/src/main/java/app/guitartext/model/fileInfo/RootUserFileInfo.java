package app.guitartext.model.fileInfo;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class RootUserFileInfo implements UserFilesInfo
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
}
