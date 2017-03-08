package app.guitartext.ui.presenters.fileCategory.impl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;


import com.noveogroup.android.log.Log;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.ArrayList;
import java.util.List;

import app.guitartext.R;
import app.guitartext.ui.browser.FileBrowseActivity;
import app.guitartext.ui.presenters.fileCategory.FileCategoryEntry;
import app.guitartext.ui.presenters.fileCategory.FileCategoryPresenter;
import app.guitartext.ui.presenters.fileCategory.SubFileCategoryEntry;
import app.guitartext.ui.start.ExpendableListEntry;
import app.guitartext.ui.tekst.TextActivity;
import app.guitartext.user.UserState;
import app.guitartext.user.fileInfo.FileInfo;
import app.guitartext.user.fileInfo.UserFilesInfo;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class FileCategoryPresenterImpl implements FileCategoryPresenter
{
	private static final Logger logger = LoggerManager.getLogger();

	private final Context context;
	private final UserFilesInfo userFilesInfo;
	private final UserState userState;

	private List<FileCategoryEntry> fileCategoryEntryList;


	public FileCategoryPresenterImpl(Context ctx, UserFilesInfo userFilesInfo, UserState userState)
	{
		this.context = ctx;
		this.userFilesInfo = userFilesInfo;
		this.userState = userState;
		fileCategoryEntryList = new ArrayList<>();

		addBaseCategory();
		addFavouritesCategory();
		addRecentCategory();
	}

	@Override
	public ExpendableListEntry getCategoryEntry(int categoryPosition)
	{
		return getFileCategory(categoryPosition);
	}

	@Override
	public ExpendableListEntry getSubCategoryEntry(int categoryPosition, int subCategoryPosition)
	{
		return getSubFileCategory(categoryPosition, subCategoryPosition);
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

	@Override
	public void categorySelected(int categoryPosition)
	{
		logger.d("dupa");
	}

	@Override
	public void subCategorySelected(int categoryPosition, int subCategoryPosition)
	{
		SubFileCategoryEntry subFileCategoryEntry = getSubFileCategory(categoryPosition, subCategoryPosition);
		if(subFileCategoryEntry == null) return;

		FileInfo selectedFileInfo = subFileCategoryEntry.getFileInfo();
		userState.setLastActiveFile(selectedFileInfo);
		if(selectedFileInfo.isDirectory())
		{
			startFileBrowseActivity();
		}
		else
		{
			startTextActivity();
		}
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
							fileInfo,
							fileInfo.isDirectory() ? R.drawable.folder : R.drawable.text_plain)
			);
		}
	}

	@Nullable
	private FileCategoryEntry getFileCategory(int fileCategoryPosition)
	{
		if(fileCategoryPosition < 0 || fileCategoryPosition >= fileCategoryEntryList.size())
			return null;

		return fileCategoryEntryList.get(fileCategoryPosition);
	}

	@Nullable
	private SubFileCategoryEntry getSubFileCategory(int categoryPosition, int subCategoryPosition)
	{
		if(categoryPosition < 0 || categoryPosition >= fileCategoryEntryList.size()) return null;

		List<SubFileCategoryEntry> subCategories = fileCategoryEntryList.get(categoryPosition).getSubFileCategoryEntryList();

		if(subCategoryPosition < 0 || subCategoryPosition >= subCategories.size()) return null;

		return subCategories.get(subCategoryPosition);
	}

	private void startFileBrowseActivity()
	{
		context.startActivity(new Intent(context, FileBrowseActivity.class));
	}

	private void startTextActivity()
	{
		context.startActivity(new Intent(context, TextActivity.class));
	}
}
