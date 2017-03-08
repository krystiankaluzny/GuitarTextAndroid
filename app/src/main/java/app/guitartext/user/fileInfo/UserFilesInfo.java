package app.guitartext.user.fileInfo;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface UserFilesInfo
{
	@NonNull List<FileInfo> getBaseFiles();

	@NonNull List<FileInfo> getFavouriteFiles();

	@NonNull List<FileInfo> getRecentOpenedFiles();
}
