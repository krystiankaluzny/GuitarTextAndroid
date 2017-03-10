package app.guitartext.dagger.user;

import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.model.user.RootUserFileInfo;
import app.guitartext.model.user.UserFilesInfo;
import app.guitartext.model.user.UserState;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 06.03.2017.
 * Modified by
 */

@Module
public class UserModule
{
	@UserScope
	@Provides
	UserFilesInfo provideUserFilesInfo()
	{
		return new RootUserFileInfo();
//		return new RandomUserFilesInfo();
	}

	@UserScope
	@Provides
	UserState provideUserState()
	{
		return new UserState();
	}
}
