package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

@DatabaseTable
public class OpenedFile extends BaseTable
{
	@DatabaseField(foreign=true, columnName=User.FOREIGN_ID)
	private User user;

	@DatabaseField
	private String path;

	@DatabaseField
	private String charset;

	@DatabaseField
	private int openCount;

	@DatabaseField
	private int lastOpenTimestamp; //unix timestamp in sec

	public OpenedFile()
	{
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}

	public int getOpenCount()
	{
		return openCount;
	}

	public void setOpenCount(int openCount)
	{
		this.openCount = openCount;
	}

	public int getLastOpenTimestamp()
	{
		return lastOpenTimestamp;
	}

	public void setLastOpenTimestamp(int lastOpenTimestamp)
	{
		this.lastOpenTimestamp = lastOpenTimestamp;
	}
}
