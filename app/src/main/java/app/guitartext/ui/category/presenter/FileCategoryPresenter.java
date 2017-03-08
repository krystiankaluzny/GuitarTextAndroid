package app.guitartext.ui.category.presenter;

import app.guitartext.ui.category.ExpendableListEntry;

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
