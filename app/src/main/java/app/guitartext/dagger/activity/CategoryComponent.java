package app.guitartext.dagger.activity;

import app.guitartext.dagger.scopes.ActivityScope;
import app.guitartext.ui.category.CategoryActivity;
import dagger.Subcomponent;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ActivityScope
@Subcomponent(modules = CategoryModule.class)
public interface CategoryComponent
{
	void inject(CategoryActivity categoryActivity);
}
