package app.guitartext.dagger.user;

import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.model.fileInfo.RootUserFileInfo;
import app.guitartext.model.fileInfo.UserFilesInfo;
import app.guitartext.user.UserState;
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
