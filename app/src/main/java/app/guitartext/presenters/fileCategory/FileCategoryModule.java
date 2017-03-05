package app.guitartext.presenters.fileCategory;

import android.content.Context;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.presenters.fileCategory.impl.FileCategoryPresenterImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class FileCategoryModule
{
	@ActivityScope
	@Provides
	public FileCategoryPresenter provideFileCategoryPresenter(Context context)
	{
		return new FileCategoryPresenterImpl(context);
	}
}
