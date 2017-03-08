package app.guitartext.ui.browser.component;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.browser.FileBrowserActivity;
import dagger.Subcomponent;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

@ActivityScope
@Subcomponent(modules = FileBrowserModule.class)
public interface FileBrowserComponent
{
	void inject(FileBrowserActivity fileBrowserActivity);
}
