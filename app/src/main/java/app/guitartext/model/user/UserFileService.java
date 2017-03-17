package app.guitartext.model.user;

import android.support.annotation.NonNull;

import java.util.List;

import app.guitartext.model.fileInfo.FileInfo;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface UserFileService
{
	@NonNull List<FileInfo> getBaseFiles();

	@NonNull List<FileInfo> getFavouriteFiles();

	@NonNull List<FileInfo> getRecentOpenedFiles();
}
