package app.guitartext.dagger.user;

import com.j256.ormlite.dao.Dao;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.sql.SQLException;

import javax.inject.Named;

import app.guitartext.dagger.scopes.UserScope;
import app.guitartext.db.dao.DaoFactory;
import app.guitartext.db.schema.tables.BaseFile;
import app.guitartext.db.schema.tables.OpenedFile;
import app.guitartext.db.schema.tables.User;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

@Module
public class UserDaoModule
{
	private static final Logger logger = LoggerManager.getLogger();

	private User defaultUser = new User("default_user");

	public UserDaoModule()
	{
		defaultUser.setId(1);
	}

	@UserScope
	@Provides
	Dao<User, Integer> provideUserDao(DaoFactory daoFactory)
	{
		return daoFactory.getDao(User.class);
	}

	@UserScope
	@Provides
	Dao<BaseFile, Integer> provideBaseFileDao(DaoFactory daoFactory)
	{
		return daoFactory.getDao(BaseFile.class);
	}

	@UserScope
	@Provides
	Dao<OpenedFile, Integer> provideOpenedFileDao(DaoFactory daoFactory)
	{
		return daoFactory.getDao(OpenedFile.class);
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
