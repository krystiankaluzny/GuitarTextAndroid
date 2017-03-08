package app.guitartext.ui.start;

import android.content.Context;

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
	@ActivityScope
	@Provides
	public FileCategoryPresenter provideFileCategoryPresenter(Context context, UserFilesInfo userFilesInfo, UserState userState)
	{
		return new FileCategoryPresenterImpl(context, userFilesInfo, userState);
	}
}
