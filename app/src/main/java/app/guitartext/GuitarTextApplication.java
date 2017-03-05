package app.guitartext;

import android.app.Application;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class GuitarTextApplication extends Application
{
	private ApplicationComponent applicationComponent;

	@Override
	public void onCreate()
	{
		super.onCreate();

		applicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this)).build();
	}

	public ApplicationComponent getApplicationComponent()
	{
		return applicationComponent;
	}
}
