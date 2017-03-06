package app.guitartext.user.fileInfo;

import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface UserFilesInfo
{
	List<FileInfo> getBaseFiles();

	List<FileInfo> getFavouriteFiles();

	List<FileInfo> getRecentOpenedFiles();
}
