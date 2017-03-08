package app.guitartext.ui.start;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.user.UserComponent2;
import dagger.Component;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ActivityScope
//@Component(dependencies = UserComponent2.class, modules = MainActivityModule.class)
public interface MainActivityComponent2
{
	void inject(MainActivity mainActivity);

	ExpendableListAdapter expendableListAdapter();
}
