package app.guitartext.db.schema.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 *
 * This structure can provide data for creating favourite file list and recent file list.
 */

@NoArgsConstructor
@Data
@DatabaseTable
public class OpenedFile extends BaseTable
{
	public static final String OPEN_COUNT = "openCount";
	public static final String LAST_OPEN_TIMESTAMP = "lastOpenTimestamp";

	@DatabaseField(foreign = true, columnName = User.FOREIGN_ID)
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private User user;

	@DatabaseField
	private String path;

	@DatabaseField
	private String charset;

	@DatabaseField(columnName = OPEN_COUNT)
	private int openCount;

	@DatabaseField(columnName = LAST_OPEN_TIMESTAMP)
	private int lastOpenTimestamp; //unix timestamp in sec
}
