package app.guitartext.dagger.app;

import android.content.Context;

import app.guitartext.dagger.scopes.ApplicationScope;
import app.guitartext.model.fileInfo.FileInfoService;
import app.guitartext.model.fileInfo.FileSystemFileInfoService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class ApplicationModule
{
	private final Context context;

	public ApplicationModule(Context context)
	{
		this.context = context.getApplicationContext();
	}

	@ApplicationScope
	@Provides
	public Context provideApplicationContext()
	{
		return context;
	}

	@ApplicationScope
	@Provides
	public FileInfoService provideFileInfoService(FileSystemFileInfoService impl)
	{
		return impl;
	}
}
