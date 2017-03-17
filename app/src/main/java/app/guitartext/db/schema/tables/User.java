package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

@DatabaseTable
public class User extends BaseTable
{
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
