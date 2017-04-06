package app.guitartext;

import android.app.Application;
import android.content.Context;

import app.guitartext.dagger.app.ApplicationComponent;
import app.guitartext.dagger.app.ApplicationModule;
import app.guitartext.dagger.app.DaggerApplicationComponent;
import app.guitartext.dagger.user.UserComponent;
import app.guitartext.dagger.user.UserModule;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class GuitarTextApplication extends Application
{
	private ApplicationComponent applicationComponent;
	private UserComponent userComponent;

	public static GuitarTextApplication get(Context context)
	{
		return (GuitarTextApplication) context.getApplicationContext();
	}

	@Override
	public void onCreate()
	{
		super.onCreate();

		applicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();

		userComponent = applicationComponent.plus(new UserModule());
	}

	public ApplicationComponent getApplicationComponent()
	{
		return applicationComponent;
	}

	public UserComponent getUserComponent()
	{
		return userComponent;
	}
}
