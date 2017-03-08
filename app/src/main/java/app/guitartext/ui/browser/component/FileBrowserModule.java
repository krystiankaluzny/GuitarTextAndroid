package app.guitartext.ui.browser.component;

import android.app.Activity;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;
import app.guitartext.ui.browser.presenter.impl.FileBrowserPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 08.03.2017.
 * Modified by
 */

@Module
public class FileBrowserModule
{
	private final Activity activity;

	public FileBrowserModule(Activity activity)
	{
		this.activity = activity;
	}

	@ActivityScope
	@Provides
	FileBrowserPresenter provideFileBrowserPresenter()
	{
		return new FileBrowserPresenterImpl(activity);
	}
}
