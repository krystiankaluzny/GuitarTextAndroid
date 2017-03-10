package app.guitartext.dagger.activity;

import app.guitartext.dagger.scopes.ActivityScope;
import app.guitartext.ui.browser.FileBrowserActivity;
import app.guitartext.model.fileInfo.FileInfo;
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

	FileInfo startFileLocation();
}
