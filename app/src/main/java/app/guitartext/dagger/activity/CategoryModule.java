package app.guitartext.dagger.activity;

import android.app.Activity;

import app.guitartext.dagger.scopes.ActivityScope;
import app.guitartext.presenter.category.FileCategoryPresenter;
import app.guitartext.presenter.category.impl.FileCategoryPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class CategoryModule
{
	private final Activity activity;
	private final FileCategoryPresenter.View view;

	public CategoryModule(Activity activity, FileCategoryPresenter.View view)
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
	public FileCategoryPresenter.View provideView()
	{
		return view;
	}

	@ActivityScope
	@Provides
	public FileCategoryPresenter provideFileCategoryPresenter(FileCategoryPresenterImpl impl)
	{
		return impl;
	}
}
