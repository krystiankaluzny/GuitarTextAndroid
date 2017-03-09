package app.guitartext.ui.text.component;

import android.app.Activity;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.text.presenter.TextPresenter;
import app.guitartext.ui.text.presenter.impl.TextPresenterImpl;
import app.guitartext.user.fileInfo.FileInfo;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

@Module
public class TextModule
{
	private final Activity activity;
	private final TextPresenter.View view;

	public TextModule(Activity activity, TextPresenter.View view)
	{
		this.activity = activity;
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
	public TextPresenter.View provideView()
	{
		return view;
	}

	@ActivityScope
	@Provides
	TextPresenter provideTextPresenter(TextPresenterImpl impl)
	{
		return impl;
	}
}
