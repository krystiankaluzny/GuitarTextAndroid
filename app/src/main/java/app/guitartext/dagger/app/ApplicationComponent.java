package app.guitartext.dagger.app;

import android.content.Context;

import app.guitartext.dagger.scopes.ApplicationScope;
import app.guitartext.dagger.user.UserComponent;
import app.guitartext.dagger.user.UserModule;
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
