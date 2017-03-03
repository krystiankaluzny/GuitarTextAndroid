package org.obywatel.guitartext.presenters.fileCategory;

import org.obywatel.guitartext.start.ExpendableListEntry;

/**
 * Created by obywatel on 03.03.2017.
 * Modified by
 */

public interface FileCategoryPresenter
{
	ExpendableListEntry getGroupEntry(int groupPosition);

	ExpendableListEntry getChildEntry(int groupPosition, int childPosition);

	int getGroupCount();

	int getChildrenCount(int groupPosition);
}
