package app.guitartext.user;

import app.guitartext.scopes.UserScope;
import app.guitartext.ui.browser.component.FileBrowserComponent;
import app.guitartext.ui.browser.component.FileBrowserModule;
import app.guitartext.ui.category.component.CategoryComponent;
import app.guitartext.ui.category.component.CategoryModule;
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

	CategoryComponent plus(CategoryModule categoryModule);

	FileBrowserComponent plus(FileBrowserModule fileBrowserModule);
}
