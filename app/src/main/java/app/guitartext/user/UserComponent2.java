package app.guitartext.user;

import app.guitartext.ApplicationComponent2;
import app.guitartext.scopes.UserScope;
import app.guitartext.user.fileInfo.UserFilesInfo;
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
