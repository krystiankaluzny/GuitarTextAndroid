package org.obywatel.guitartext.presenters.fileCategory.impl;

import android.content.Context;

import org.obywatel.guitartext.R;
import org.obywatel.guitartext.model.FileInfo;
import org.obywatel.guitartext.model.RandomUserFilesInfo;
import org.obywatel.guitartext.model.UserFilesInfo;
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

	private UserFilesInfo userFilesInfo = new RandomUserFilesInfo();

	public FileCategoryPresenterImpl(Context ctx)
	{
		this.context = ctx.getApplicationContext();
		fileCategoryEntryList = new ArrayList<>();

		addBaseCategory();
		addFavouritesCategory();
		addRecentCategory();
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

	private void addBaseCategory()
	{
		FileCategoryEntry baseCategory = new FileCategoryEntry(context.getResources().getString(R.string.category_base), android.R.drawable.btn_radio);
		addSubEntryToCategory(baseCategory, userFilesInfo.getBaseFiles());

		fileCategoryEntryList.add(baseCategory);
	}

	private void addFavouritesCategory()
	{
		FileCategoryEntry favouritesCategory = new FileCategoryEntry(context.getResources().getString(R.string.category_favourite), android.R.drawable.btn_star);
		addSubEntryToCategory(favouritesCategory, userFilesInfo.getFavouriteFiles());

		fileCategoryEntryList.add(favouritesCategory);
	}

	private void addRecentCategory()
	{
		FileCategoryEntry recentCategory = new FileCategoryEntry(context.getResources().getString(R.string.category_recent), android.R.drawable.btn_star_big_off);
		addSubEntryToCategory(recentCategory, userFilesInfo.getRecentOpenedFiles());

		fileCategoryEntryList.add(recentCategory);
	}

	private void addSubEntryToCategory(FileCategoryEntry fileCategoryEntry, List<FileInfo> fileInfoList)
	{
		for(FileInfo fileInfo : fileInfoList)
		{
			fileCategoryEntry.addFileEntry(
					new SubFileCategoryEntry(
							fileInfo.getName(),
							fileInfo.isDirectory() ? R.drawable.folder : R.drawable.text_plain)
			);
		}
	}
}
