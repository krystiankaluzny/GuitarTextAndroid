package app.guitartext.presenter.category.impl;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.guitartext.R;
import app.guitartext.model.fileInfo.FileInfo;
import app.guitartext.model.fileInfo.ParcelableFileInfoWrapper;
import app.guitartext.model.user.UserFileService;
import app.guitartext.presenter.category.FileCategoryEntry;
import app.guitartext.presenter.category.FileCategoryPresenter;
import app.guitartext.presenter.category.SubFileCategoryEntry;
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
	private final FileCategoryPresenter.View view;
	private final Activity activity;

	private List<FileCategoryEntry> fileCategoryEntryList;

	@Inject
	public FileCategoryPresenterImpl(Activity activity, UserFileService userFileService, FileCategoryPresenter.View view)
	{
		this.activity = activity;
		this.userFileService = userFileService;
		this.view = view;
		fileCategoryEntryList = new ArrayList<>();
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

	@Override
	public void updateCategory()
	{
		createCategories();
		view.onCategoriesUpdated(fileCategoryEntryList);
	}

	private void createCategories()
	{
		List<FileCategoryEntry> categoryEntries = new ArrayList<>(3);
		categoryEntries.add(createBaseCategory());
		categoryEntries.add(createFavouritesCategory());
		categoryEntries.add(createRecentCategory());

		fileCategoryEntryList = ImmutableList.copyOf(categoryEntries);
	}

	private FileCategoryEntry createBaseCategory()
	{
		List<SubFileCategoryEntry> sub = immutableSubEntryList(userFileService.getBaseFiles());

		return new FileCategoryEntry(activity.getResources().getString(R.string.category_base), R.drawable.abs_base, sub);
	}

	private FileCategoryEntry createFavouritesCategory()
	{
		List<SubFileCategoryEntry> sub = immutableSubEntryList(userFileService.getFavouriteFiles());

		return new FileCategoryEntry(activity.getResources().getString(R.string.category_favourite), R.drawable.abs_favourite, sub);
	}

	private FileCategoryEntry createRecentCategory()
	{
		List<SubFileCategoryEntry> sub = immutableSubEntryList(userFileService.getRecentOpenedFiles());

		return new FileCategoryEntry(activity.getResources().getString(R.string.category_recent), R.drawable.abs_recent, sub);
	}

	private List<SubFileCategoryEntry> immutableSubEntryList(List<FileInfo> fileInfoList)
	{
		List<SubFileCategoryEntry> subFileCategoryEntries = new ArrayList<>(fileInfoList.size());
		for(FileInfo fileInfo : fileInfoList)
		{
			subFileCategoryEntries.add(
					new SubFileCategoryEntry(
							fileInfo,
							fileInfo.isDirectory() ? R.drawable.abs_folder : R.drawable.abs_file)
			);
		}

		return ImmutableList.copyOf(subFileCategoryEntries);
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
