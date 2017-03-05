package app.guitartext;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent
{
	Context appContext();
}
