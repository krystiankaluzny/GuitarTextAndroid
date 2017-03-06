package app.guitartext.user;

import android.content.Context;

import app.guitartext.scopes.UserScope;
import app.guitartext.user.fileInfo.RandomUserFilesInfo;
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
		return new RandomUserFilesInfo();
	}
}
