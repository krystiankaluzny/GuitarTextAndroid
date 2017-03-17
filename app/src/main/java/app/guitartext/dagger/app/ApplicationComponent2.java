package app.guitartext.dagger.app;

import android.content.Context;

import app.guitartext.dagger.scopes.ApplicationScope;
import app.guitartext.db.dao.DaoFactory;
import dagger.Component;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent2
{
	Context appContext();

	DaoFactory daoFactory();
}
