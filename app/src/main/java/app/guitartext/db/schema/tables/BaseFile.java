package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@DatabaseTable
public class BaseFile extends BaseTable
{
	@DatabaseField(foreign = true, columnName = User.FOREIGN_ID)
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private User user;

	@DatabaseField
	private String path;

	@DatabaseField
	private boolean directory;
}
