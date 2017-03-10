package app.guitartext.model.user;

import app.guitartext.model.fileInfo.FileInfo;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class UserState
{
	private FileInfo lastActiveFile;

	public FileInfo getLastActiveFile()
	{
		return lastActiveFile;
	}

	public void setLastActiveFile(FileInfo lastActiveFile)
	{
		this.lastActiveFile = lastActiveFile;
	}
}
