package app.guitartext.presenter.category.impl;

import java.util.ArrayList;
import java.util.List;

import app.guitartext.presenter.category.ExpendableListEntry;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class FileCategoryEntry extends ExpendableListEntry
{
	private List<SubFileCategoryEntry> subFileCategoryEntryList = new ArrayList<>();

	public FileCategoryEntry(String name)
	{
		super(name, android.R.drawable.btn_dialog);
	}

	public FileCategoryEntry(String name, int iconResourceId)
	{
		super(name, iconResourceId);
	}

	public FileCategoryEntry addFileEntry(SubFileCategoryEntry subFileCategoryEntry)
	{
		subFileCategoryEntryList.add(subFileCategoryEntry);
		return this;
	}

	public List<SubFileCategoryEntry> getSubFileCategoryEntryList()
	{
		return subFileCategoryEntryList;
	}
}
