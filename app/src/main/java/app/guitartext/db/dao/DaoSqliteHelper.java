package app.guitartext.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.sql.SQLException;

import javax.inject.Inject;

import app.guitartext.db.schema.DatabaseSchemaInfo;

/**
 * Created by obywatel on 16.03.2017.
 * Modified by
 */

public class DaoSqliteHelper extends OrmLiteSqliteOpenHelper implements DaoFactory
{
	private static final Logger logger = LoggerManager.getLogger();

	private final DatabaseSchemaInfo databaseSchemaInfo;

	@Inject
	public DaoSqliteHelper(Context context, DatabaseSchemaInfo databaseSchemaInfo)
	{
		super(context, databaseSchemaInfo.getDatabaseName(), null, databaseSchemaInfo.getDatabaseVersion());
		this.databaseSchemaInfo = databaseSchemaInfo;
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
	{
		logger.d("DaoSqliteHelper.onCreate");
		for(Class<?> tableClass: databaseSchemaInfo.getTables())
		{
			try
			{
				TableUtils.createTableIfNotExists(connectionSource, tableClass);
			} catch(SQLException e)
			{
				logger.e(e);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
	{
		logger.d("DaoSqliteHelper.onUpgrade");
		for(Class<?> tableClass: databaseSchemaInfo.getTables())
		{
			try
			{
				TableUtils.dropTable(connectionSource, tableClass, false);
			} catch(SQLException e)
			{
				logger.e(e);
			}
		}

		onCreate(database, connectionSource);
	}

	@Override
	public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz)
	{
		D dao = null;
		try
		{
			dao =  super.getDao(clazz);
		} catch(SQLException e)
		{
			logger.e(e);
		}
		return dao;
	}
}
