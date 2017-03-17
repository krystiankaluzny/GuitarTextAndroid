package app.guitartext.db.schema.tables;

import com.google.common.base.Strings;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

@DatabaseTable
public class User extends BaseTable
{
	public static final String FOREIGN_ID = "user_id";

	@DatabaseField
	private String name;

	public User()
	{
	}

	public User(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
