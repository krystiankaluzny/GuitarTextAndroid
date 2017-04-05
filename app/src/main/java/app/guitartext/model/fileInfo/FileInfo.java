package app.guitartext.model.fileInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

@RequiredArgsConstructor
@Getter
public class FileInfo
{
	private final int id;
	private final boolean directory;
	private final String path;
	private final String name;
}
