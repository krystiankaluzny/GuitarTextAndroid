package app.guitartext.presenter.category.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;
import app.guitartext.model.user.UserFileService;
import app.guitartext.presenter.category.ExpendableListEntry;
import app.guitartext.presenter.category.FileCategoryPresenter;
import app.guitartext.ui.browser.FileBrowserActivity;
import app.guitartext.ui.text.TextActivity;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public class FileCategoryPresenterImpl implements FileCategoryPresenter
{
	private static final Logger logger = LoggerManager.getLogger();

	private final UserFileService userFileService;
	private final Activity activity;

	private List<FileCategoryEntry> fileCategoryEntryList;

	@Inject
	public FileCategoryPresenterImpl(Activity activity, UserFileService userFileService)
	{
		this.activity = activity;
		this.userFileService = userFileService;
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
		//DO NOTHING
	}

	@Override
	public void subCategorySelected(int categoryPosition, int subCategoryPosition)
	{
		SubFileCategoryEntry subFileCategoryEntry = getSubFileCategory(categoryPosition, subCategoryPosition);
		if(subFileCategoryEntry == null) return;

		FileInfo selectedFileInfo = subFileCategoryEntry.getFileInfo();

		Intent intent;
		if(selectedFileInfo.isDirectory())
		{
			intent = new Intent(activity, FileBrowserActivity.class);
		}
		else
		{
			intent = new Intent(activity, TextActivity.class);
		}

		intent.putExtra(ParcelableFileInfoWrapper.EXTRA_FILE_INFO, ParcelableFileInfoWrapper.wrap(selectedFileInfo));
		activity.startActivity(intent);
	}

	private void addBaseCategory()
	{
		FileCategoryEntry baseCategory = new FileCategoryEntry(activity.getResources().getString(R.string.category_base), R.drawable.abs_base);
		addSubEntryToCategory(baseCategory, userFileService.getBaseFiles());

		fileCategoryEntryList.add(baseCategory);
	}

	private void addFavouritesCategory()
	{
		FileCategoryEntry favouritesCategory = new FileCategoryEntry(activity.getResources().getString(R.string.category_favourite), R.drawable.abs_favourite);
		addSubEntryToCategory(favouritesCategory, userFileService.getFavouriteFiles());

		fileCategoryEntryList.add(favouritesCategory);
	}

	private void addRecentCategory()
	{
		FileCategoryEntry recentCategory = new FileCategoryEntry(activity.getResources().getString(R.string.category_recent), R.drawable.abs_recent);
		addSubEntryToCategory(recentCategory, userFileService.getRecentOpenedFiles());

		fileCategoryEntryList.add(recentCategory);
	}

	private void addSubEntryToCategory(FileCategoryEntry fileCategoryEntry, List<FileInfo> fileInfoList)
	{
		for(FileInfo fileInfo : fileInfoList)
		{
			fileCategoryEntry.addFileEntry(
					new SubFileCategoryEntry(
							fileInfo,
							fileInfo.isDirectory() ? R.drawable.abs_folder : R.drawable.abs_file)
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
}
