package app.guitartext.dagger.user;

import app.guitartext.dagger.app.ApplicationComponent2;
import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.model.user.UserFilesInfo;
import dagger.Component;

/**
 * Created by obywatel on 06.03.2017.
 * Modified by
 */

@UserScope
@Component(dependencies = ApplicationComponent2.class, modules = UserModule.class)
public interface UserComponent2
{
	UserFilesInfo userFilesInfo();
}
