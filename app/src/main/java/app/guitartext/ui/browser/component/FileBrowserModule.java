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
	private final FileBrowserPresenter.View view;

	public FileBrowserModule(Activity activity, FileInfo fileInfo, FileBrowserPresenter.View view)
	{
		this.activity = activity;
		this.fileInfo = fileInfo;
		this.view = view;
	}

	@ActivityScope
	@Provides
	public Activity provideActivity()
	{
		return activity;
	}

	@ActivityScope
	@Provides
	public FileInfo provideFileInfo()
	{
		return fileInfo;
	}

	@ActivityScope
	@Provides
	public FileBrowserPresenter.View provideView()
	{
		return view;
	}

	@ActivityScope
	@Provides
	FileBrowserPresenter provideFileBrowserPresenter(FileBrowserPresenterImpl impl)
	{
		return impl;
	}
}
