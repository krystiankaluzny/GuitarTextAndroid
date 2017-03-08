package app.guitartext.ui.start;

import android.app.Activity;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.presenters.fileCategory.FileCategoryPresenter;
import app.guitartext.ui.presenters.fileCategory.impl.FileCategoryPresenterImpl;
import app.guitartext.user.UserState;
import app.guitartext.user.fileInfo.UserFilesInfo;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class MainActivityModule
{
	private final Activity activity;

	public MainActivityModule(Activity activity)
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
