package app.guitartext.presenter.category;

import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class FileCategoryEntry extends ExpendableListEntry
{
	private final List<SubFileCategoryEntry> subFileCategoryEntryList;

	public FileCategoryEntry(String name, int iconResourceId, List<SubFileCategoryEntry> subFileCategoryEntryList)
	{
		super(name, iconResourceId);
		this.subFileCategoryEntryList = subFileCategoryEntryList;
	}

	public List<SubFileCategoryEntry> getSubFileCategoryEntryList()
	{
		return subFileCategoryEntryList;
	}
}
