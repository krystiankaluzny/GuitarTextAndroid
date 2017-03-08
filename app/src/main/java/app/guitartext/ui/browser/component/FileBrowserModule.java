package app.guitartext.ui.browser.component;

import android.app.Activity;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.browser.presenter.FileBrowserPresenter;
import app.guitartext.ui.browser.presenter.impl.FileBrowserPresenterImpl;
import app.guitartext.user.fileInfo.FileInfo;
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
	private final FileInfo fileInfo;

	public FileBrowserModule(Activity activity, FileInfo fileInfo)
	{
		this.activity = activity;
		this.fileInfo = fileInfo;
	}

	@ActivityScope
	@Provides
	FileBrowserPresenter provideFileBrowserPresenter()
	{
		return new FileBrowserPresenterImpl(activity, fileInfo);
	}
}
