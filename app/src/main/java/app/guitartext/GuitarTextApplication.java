package app.guitartext;

import android.app.Application;
import android.content.Context;

import app.guitartext.user.DaggerUserComponent2;
import app.guitartext.user.UserComponent;
import app.guitartext.user.UserComponent2;
import app.guitartext.user.UserModule;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class GuitarTextApplication extends Application
{
	private ApplicationComponent applicationComponent;
	private UserComponent userComponent;

	private ApplicationComponent2 applicationComponent2;
	private UserComponent2 userComponent2;

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

		applicationComponent2 = DaggerApplicationComponent2.builder()
				.applicationModule(new ApplicationModule(this))
				.build();

		userComponent2 = DaggerUserComponent2.builder()
				.applicationComponent2(applicationComponent2)
				.build();
	}

	public ApplicationComponent getApplicationComponent()
	{
		return applicationComponent;
	}

	public UserComponent getUserComponent()
	{
		return userComponent;
	}

	public ApplicationComponent2 getApplicationComponent2()
	{
		return applicationComponent2;
	}

	public UserComponent2 getUserComponent2()
	{
		return userComponent2;
	}
}
