package app.guitartext.user;

import app.guitartext.scopes.UserScope;
import app.guitartext.ui.start.MainActivityComponent;
import app.guitartext.ui.start.MainActivityModule;
import app.guitartext.user.fileInfo.UserFilesInfo;
import dagger.Subcomponent;

/**
 * Created by obywatel on 06.03.2017.
 * Modified by
 */

@UserScope
@Subcomponent(modules = UserModule.class)
public interface UserComponent
{
	UserFilesInfo userFilesInfo();

	MainActivityComponent plus(MainActivityModule mainActivityModule);
}
