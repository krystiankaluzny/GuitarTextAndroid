package app.guitartext.model.fileInfo;

import java.util.List;

/**
 * Created by obywatel on 10.03.2017.
 * Modified by
 */
public interface FileInfoService
{
	FileInfo getRootLocation();

	/**
	 * If {@code fileInfo} is valid directory return it.
	 * If {@code fileInfo} is valid file return it's parent directory.
	 * If {{@code fileInfo} is invalid file info for root will be returned.
	 *
	 * @param fileInfo
	 * @return
	 */
	FileInfo getValidateDirectory(FileInfo fileInfo);

	List<FileInfo> getLocationContent(FileInfo location);

	List<FileInfo> getFileWithAncestors(FileInfo fileInfo);

	String readFile(FileInfo fileInfo);

	List<String> readFileLines(FileInfo fileInfo);
}
