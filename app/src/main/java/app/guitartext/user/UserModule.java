package app.guitartext.user;

import app.guitartext.scopes.UserScope;
import app.guitartext.user.fileInfo.RootUserFileInfo;
import app.guitartext.user.fileInfo.UserFilesInfo;
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
