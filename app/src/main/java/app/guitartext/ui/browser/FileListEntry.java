package app.guitartext.ui.browser;

import app.guitartext.ui.ListEntry;
import app.guitartext.user.fileInfo.FileInfo;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class FileListEntry extends ListEntry
{
	private final FileInfo fileInfo;

	public FileListEntry(FileInfo fileInfo, int iconResourceId)
	{
		super(fileInfo.getName(), iconResourceId);
		this.fileInfo = fileInfo;
	}

	public FileInfo getFileInfo()
	{
		return fileInfo;
	}
}
