package app.guitartext.dagger.user;

import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.model.user.UserFileService;
import app.guitartext.model.user.UserState;
import app.guitartext.model.user.impl.MainUserFileService;
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
	UserFileService provideUserFilesInfo(MainUserFileService impl)
	{
		return impl;
	}

	@UserScope
	@Provides
	UserState provideUserState()
	{
		return new UserState();
	}
}
