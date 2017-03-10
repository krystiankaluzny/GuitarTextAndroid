package app.guitartext.dagger.activity;

import android.app.Activity;

import app.guitartext.dagger.scopes.ActivityScope;
import app.guitartext.presenter.browser.FileBrowserPresenter;
import app.guitartext.presenter.browser.impl.FileBrowserPresenterImpl;
import app.guitartext.model.fileInfo.FileInfo;
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
