package app.guitartext;

import android.content.Context;

import javax.inject.Singleton;

import app.guitartext.scopes.ApplicationScope;
import app.guitartext.user.UserComponent;
import app.guitartext.user.UserModule;
import dagger.Component;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent
{
	Context appContext();

	UserComponent plus(UserModule userModule);
}
