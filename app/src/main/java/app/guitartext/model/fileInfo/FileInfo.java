package app.guitartext.model.fileInfo;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */


public class FileInfo
{
	private final int id;
	private final boolean directory;
	private final String path;
	private final String name;

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
