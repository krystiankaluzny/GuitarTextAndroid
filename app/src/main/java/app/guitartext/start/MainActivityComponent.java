package app.guitartext.start;

import app.guitartext.ApplicationComponent;
import app.guitartext.scopes.ActivityScope;
import app.guitartext.presenters.fileCategory.FileCategoryModule;
import dagger.Component;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = FileCategoryModule.class)
public interface MainActivityComponent
{
	void inject(MainActivity mainActivity);

	ExpendableListAdapter expendableListAdapter();
}
