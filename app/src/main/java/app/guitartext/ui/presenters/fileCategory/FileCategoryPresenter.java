package app.guitartext.ui.presenters.fileCategory;

import app.guitartext.ui.start.ExpendableListEntry;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface FileCategoryPresenter
{
	ExpendableListEntry getCategoryEntry(int categoryPosition);

	ExpendableListEntry getSubCategoryEntry(int categoryPosition, int subCategoryPosition);

	int getCategoryCount();

	int getSubCategoryCount(int groupPosition);

	void categorySelected(int categoryPosition);

	void subCategorySelected(int categoryPosition, int subCategoryPosition);
}
