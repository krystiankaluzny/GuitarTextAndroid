package app.guitartext.ui.category.component;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.category.CategoryActivity;
import app.guitartext.ui.category.CategoryExpendableListAdapter;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ActivityScope
//@Component(dependencies = UserComponent2.class, modules = CategoryActivityModule.class)
public interface CategoryActivityComponent2
{
	void inject(CategoryActivity categoryActivity);

	CategoryExpendableListAdapter expendableListAdapter();
}
