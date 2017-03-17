package app.guitartext.dagger.user;

import com.j256.ormlite.dao.Dao;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.sql.SQLException;

import javax.inject.Named;

import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.db.dao.DaoFactory;
import app.guitartext.db.schema.tables.User;
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
	private static final Logger logger = LoggerManager.getLogger();

	private User defaultUser = new User("default_user");

	public UserModule()
	{
		defaultUser.setId(1);
	}

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

	@UserScope
	@Provides
	Dao<User, Integer> provideUserDao(DaoFactory daoFactory)
	{
		return daoFactory.getDao(User.class);
	}

	@Named(value = "defaultUser")
	@UserScope
	@Provides
	User provideDefaultUser(Dao<User, Integer> dao)
	{
		try
		{
			defaultUser = dao.createIfNotExists(defaultUser);
		} catch(SQLException e)
		{
			logger.e(e);
		}
		return defaultUser;
	}

	@UserScope
	@Provides
	User provideUser(Dao<User, Integer> dao)
	{
		return provideDefaultUser(dao);
	}
}
