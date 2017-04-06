package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@DatabaseTable
public class User extends BaseTable
{
	public static final String FOREIGN_ID = "user_id";

	@DatabaseField
	private String name;
}
