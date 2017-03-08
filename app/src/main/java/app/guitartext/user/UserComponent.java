package app.guitartext.user;

import app.guitartext.scopes.UserScope;
import app.guitartext.ui.category.component.CategoryActivityComponent;
import app.guitartext.ui.category.component.CategoryActivityModule;
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

	CategoryActivityComponent plus(CategoryActivityModule categoryActivityModule);
}
