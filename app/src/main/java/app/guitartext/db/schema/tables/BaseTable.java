package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

public abstract class BaseTable
{
	@DatabaseField(generatedId=true)
	private int id;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
