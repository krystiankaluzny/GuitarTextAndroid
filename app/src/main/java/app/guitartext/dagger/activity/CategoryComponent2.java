package app.guitartext.dagger.activity;

import app.guitartext.dagger.scopes.ActivityScope;
import app.guitartext.ui.category.CategoryActivity;
import app.guitartext.ui.category.CategoryExpendableListAdapter;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ActivityScope
//@Component(dependencies = UserComponent2.class, modules = CategoryModule.class)
public interface CategoryComponent2
{
	void inject(CategoryActivity categoryActivity);

	CategoryExpendableListAdapter expendableListAdapter();
}
