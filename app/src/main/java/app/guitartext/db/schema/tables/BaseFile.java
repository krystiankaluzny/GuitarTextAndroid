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
	@DatabaseField(foreign=true, columnName="user_id")
	private User user;

	@DatabaseField
	private String path;

	@DatabaseField
	private boolean directory;

	public BaseFile()
	{
	}
}
