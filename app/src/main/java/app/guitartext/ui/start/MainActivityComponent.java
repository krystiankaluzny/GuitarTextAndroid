package app.guitartext.ui.start;

import app.guitartext.scopes.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent
{
	void inject(MainActivity mainActivity);

	ExpendableListAdapter expendableListAdapter();
}
