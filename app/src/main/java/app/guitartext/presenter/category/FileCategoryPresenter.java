package app.guitartext.presenter.category;

import java.util.List;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface FileCategoryPresenter
{
	void categorySelected(int categoryPosition);

	void subCategorySelected(int categoryPosition, int subCategoryPosition);

	void updateCategory();

	interface View
	{
		void onCategoriesUpdated(List<FileCategoryEntry> categoryEntryList);
	}
}
