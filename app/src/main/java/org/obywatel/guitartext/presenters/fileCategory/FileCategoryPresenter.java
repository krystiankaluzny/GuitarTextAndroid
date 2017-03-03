package org.obywatel.guitartext.presenters.fileCategory;

import org.obywatel.guitartext.start.ExpendableListEntry;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface FileCategoryPresenter
{
	ExpendableListEntry getCategoryEntry(int groupPosition);

	ExpendableListEntry getSubCategoryEntry(int groupPosition, int childPosition);

	int getCategoryCount();

	int getSubCategoryCount(int groupPosition);
}
