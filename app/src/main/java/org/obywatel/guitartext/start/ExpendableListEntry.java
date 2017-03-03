package org.obywatel.guitartext.start;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class ExpendableListEntry
{
	protected String name;
	protected int iconResourceId;

	public ExpendableListEntry(String name, int iconResourceId)
	{
		this.name = name;
		this.iconResourceId = iconResourceId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getIconResourceId()
	{
		return iconResourceId;
	}

	public void setIconResourceId(int iconResourceId)
	{
		this.iconResourceId = iconResourceId;
	}
}