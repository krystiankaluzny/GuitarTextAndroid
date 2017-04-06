package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

public abstract class BaseTable
{
	@Getter
	@Setter
	@DatabaseField(generatedId=true)
	private int id;
}
