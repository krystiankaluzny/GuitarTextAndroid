package app.guitartext.user.fileInfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

@Data
@AllArgsConstructor
public class FileInfo
{
	private int id;
	private boolean directory;
	private String path;
	private String name;
}
