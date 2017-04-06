package app.guitartext.db.schema.tables;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Created by obywatel on 06.04.2017.
 * Modified by
 */

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Preferences
{
	private final User user;

	public int getMaxFavouriteCount()
	{
		return user.maxFavouriteCount;
	}

	public int getMaxRecentCount()
	{
		return user.maxRecentCount;
	}

	public String getDefaultCharset()
	{
		return user.defaultCharset;
	}
}
