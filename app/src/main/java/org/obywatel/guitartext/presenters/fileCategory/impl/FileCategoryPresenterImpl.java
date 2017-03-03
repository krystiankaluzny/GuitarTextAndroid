package org.obywatel.guitartext.presenters.fileCategory.impl;

import android.content.Context;

import org.obywatel.guitartext.R;
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
	private final Context context;
	private List<FileCategoryEntry> fileCategoryEntryList;

	public FileCategoryPresenterImpl(Context ctx)
	{
		this.context = ctx.getApplicationContext();
		fileCategoryEntryList = new ArrayList<>();

		FileCategoryEntry baseCategory = new FileCategoryEntry(context.getResources().getString(R.string.category_base));
		FileCategoryEntry favouriteCategory = new FileCategoryEntry(context.getResources().getString(R.string.category_favourite));
		FileCategoryEntry recentCategory = new FileCategoryEntry(context.getResources().getString(R.string.category_recent));

		baseCategory
				.addFileEntry(new SubFileCategoryEntry("test1"))
				.addFileEntry(new SubFileCategoryEntry("test2"))
				.addFileEntry(new SubFileCategoryEntry("test3"))
				.addFileEntry(new SubFileCategoryEntry("test4"))
				.addFileEntry(new SubFileCategoryEntry("test4"))
				.addFileEntry(new SubFileCategoryEntry("test4"));

		favouriteCategory
				.addFileEntry(new SubFileCategoryEntry("Aud1"))
				.addFileEntry(new SubFileCategoryEntry("Aud2"))
				.addFileEntry(new SubFileCategoryEntry("Aud2"))
				.addFileEntry(new SubFileCategoryEntry("Aud2"));

		recentCategory
				.addFileEntry(new SubFileCategoryEntry("Akars1"))
				.addFileEntry(new SubFileCategoryEntry("Akars2"))
				.addFileEntry(new SubFileCategoryEntry("Akars2"))
				.addFileEntry(new SubFileCategoryEntry("Akars2"))
				.addFileEntry(new SubFileCategoryEntry("Akars3"));

		fileCategoryEntryList.add(baseCategory);
		fileCategoryEntryList.add(favouriteCategory);
		fileCategoryEntryList.add(recentCategory);
	}

	@Override
	public ExpendableListEntry getCategoryEntry(int categoryPosition)
	{
		if(categoryPosition < 0 || categoryPosition >= fileCategoryEntryList.size()) return null;
		return fileCategoryEntryList.get(categoryPosition);
	}

	@Override
	public ExpendableListEntry getSubCategoryEntry(int categoryPosition, int subCategoryPosition)
	{
		if(categoryPosition < 0 || categoryPosition >= fileCategoryEntryList.size()) return null;

		List<SubFileCategoryEntry> subCategories = fileCategoryEntryList.get(categoryPosition).getSubFileCategoryEntryList();

		if(subCategoryPosition < 0 || subCategoryPosition >= subCategories.size()) return null;

		return subCategories.get(subCategoryPosition);
	}

	@Override
	public int getCategoryCount()
	{
		return fileCategoryEntryList.size();
	}

	@Override
	public int getSubCategoryCount(int categoryPosition)
	{
		if(categoryPosition < 0 || categoryPosition >= fileCategoryEntryList.size()) return 0;
		return fileCategoryEntryList.get(categoryPosition).getSubFileCategoryEntryList().size();
	}
}
