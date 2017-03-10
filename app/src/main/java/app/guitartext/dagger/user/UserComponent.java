package app.guitartext.dagger.user;

import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.dagger.activity.FileBrowserComponent;
import app.guitartext.dagger.activity.FileBrowserModule;
import app.guitartext.dagger.activity.CategoryComponent;
import app.guitartext.dagger.activity.CategoryModule;
import app.guitartext.dagger.activity.TextModule;
import app.guitartext.dagger.activity.TextComponent;
import app.guitartext.model.user.UserFilesInfo;
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

	CategoryComponent plus(CategoryModule categoryModule);

	FileBrowserComponent plus(FileBrowserModule fileBrowserModule);

	TextComponent plus(TextModule textModule);
}
