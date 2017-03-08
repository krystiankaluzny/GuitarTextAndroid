package app.guitartext.ui.category.component;

import android.app.Activity;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.category.presenter.FileCategoryPresenter;
import app.guitartext.ui.category.presenter.impl.FileCategoryPresenterImpl;
import app.guitartext.user.UserState;
import app.guitartext.user.fileInfo.UserFilesInfo;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class CategoryActivityModule
{
	private final Activity activity;

	public CategoryActivityModule(Activity activity)
	{
		this.activity = activity;
	}

	@ActivityScope
	@Provides
	public FileCategoryPresenter provideFileCategoryPresenter(UserFilesInfo userFilesInfo, UserState userState)
	{
		return new FileCategoryPresenterImpl(activity, userFilesInfo, userState);
	}
}
