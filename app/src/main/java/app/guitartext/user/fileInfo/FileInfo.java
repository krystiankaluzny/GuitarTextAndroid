package app.guitartext.user.fileInfo;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class FileInfo
{
	private int id;
	private boolean directory;
	private String path;
	private String name;

	public FileInfo(int id, boolean directory, String path, String name)
	{
		this.id = id;
		this.directory = directory;
		this.path = path;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public boolean isDirectory()
	{
		return directory;
	}

	public String getPath()
	{
		return path;
	}

	public String getName()
	{
		return name;
	}
}
