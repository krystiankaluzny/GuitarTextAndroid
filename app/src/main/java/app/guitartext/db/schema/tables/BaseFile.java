package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

@DatabaseTable
public class BaseFile extends BaseTable
{
	@DatabaseField(foreign=true, columnName=User.FOREIGN_ID)
	private User user;

	@DatabaseField
	private String path;

	@DatabaseField
	private boolean directory;

	public BaseFile()
	{
	}

	public BaseFile(User user, String path, boolean directory)
	{
		this.user = user;
		this.path = path;
		this.directory = directory;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public boolean isDirectory()
	{
		return directory;
	}

	public void setDirectory(boolean directory)
	{
		this.directory = directory;
	}
}
