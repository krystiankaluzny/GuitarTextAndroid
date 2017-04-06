package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */
@NoArgsConstructor
@DatabaseTable
public class User extends BaseTable
{
	public static final String FOREIGN_ID = "user_id";

	@DatabaseField
	@Getter
	@Setter
	private String name;

	public User(String name)
	{
		this.name = name;
	}

	//PREFERENCES
	@Getter
	private Preferences preferences = new Preferences(this);

	@DatabaseField(defaultValue = "10")
	int maxFavouriteCount;

	@DatabaseField(defaultValue = "10")
	int maxRecentCount;
}
