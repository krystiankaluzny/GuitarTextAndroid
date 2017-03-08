package app.guitartext.ui;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

public class ListEntry
{
	protected final String name;
	protected final int iconResourceId;

	public ListEntry(String name, int iconResourceId)
	{
		this.name = name;
		this.iconResourceId = iconResourceId;
	}

	public String getName()
	{
		return name;
	}

	public int getIconResourceId()
	{
		return iconResourceId;
	}
}
