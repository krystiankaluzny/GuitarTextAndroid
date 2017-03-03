package org.obywatel.guitartext.presenters.fileCategory.impl;

import org.obywatel.guitartext.presenters.fileCategory.FileCategoryEntry;
import org.obywatel.guitartext.presenters.fileCategory.FileCategoryPresenter;
import org.obywatel.guitartext.presenters.fileCategory.SubFileCategoryEntry;
import org.obywatel.guitartext.start.ExpendableListEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class FileCategoryPresenterImpl implements FileCategoryPresenter
{
	private List<FileCategoryEntry> fileCategoryEntryList;

	public FileCategoryPresenterImpl()
	{
		fileCategoryEntryList = new ArrayList<>();

		fileCategoryEntryList.add(
				new FileCategoryEntry("Test")
						.addFileEntry(new SubFileCategoryEntry("test1"))
						.addFileEntry(new SubFileCategoryEntry("test2"))
						.addFileEntry(new SubFileCategoryEntry("test3"))
						.addFileEntry(new SubFileCategoryEntry("test4")));

		fileCategoryEntryList.add(
				new FileCategoryEntry("Aud")
						.addFileEntry(new SubFileCategoryEntry("Aud1"))
						.addFileEntry(new SubFileCategoryEntry("Aud2")));

		fileCategoryEntryList.add(
				new FileCategoryEntry("Akars")
						.addFileEntry(new SubFileCategoryEntry("Akars1"))
						.addFileEntry(new SubFileCategoryEntry("Akars2"))
						.addFileEntry(new SubFileCategoryEntry("Akars3")));
	}

	@Override
	public ExpendableListEntry getGroupEntry(int groupPosition)
	{
		if(groupPosition < 0 || groupPosition >= fileCategoryEntryList.size()) return null;
		return fileCategoryEntryList.get(groupPosition);
	}

	@Override
	public ExpendableListEntry getChildEntry(int groupPosition, int childPosition)
	{
		if(groupPosition < 0 || groupPosition >= fileCategoryEntryList.size()) return null;

		List<SubFileCategoryEntry> subCategories = fileCategoryEntryList.get(groupPosition).getSubFileCategoryEntryList();

		if(childPosition < 0 || childPosition >= subCategories.size()) return null;

		return subCategories.get(childPosition);
	}

	@Override
	public int getGroupCount()
	{
		return fileCategoryEntryList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition)
	{
		if(groupPosition < 0 || groupPosition >= fileCategoryEntryList.size()) return 0;
		return fileCategoryEntryList.get(groupPosition).getSubFileCategoryEntryList().size();
	}
}
