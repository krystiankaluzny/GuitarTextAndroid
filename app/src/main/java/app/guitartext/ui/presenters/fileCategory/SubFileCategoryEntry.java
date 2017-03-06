package app.guitartext.ui.presenters.fileCategory;

import app.guitartext.ui.start.ExpendableListEntry;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class SubFileCategoryEntry extends ExpendableListEntry
{
	public SubFileCategoryEntry(String name)
	{
		super(name, android.R.drawable.btn_radio);
	}

	public SubFileCategoryEntry(String name, int iconResourceId)
	{
		super(name, iconResourceId);
	}
}
