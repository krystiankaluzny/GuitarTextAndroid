package app.guitartext;

import android.content.Context;

import javax.inject.Singleton;

import app.guitartext.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class ApplicationModule
{
	private final Context context;

	public ApplicationModule(Context context)
	{
		this.context = context.getApplicationContext();
	}


	@ApplicationScope
	@Provides
	public Context provideApplicationContext()
	{
		return context;
	}
}
