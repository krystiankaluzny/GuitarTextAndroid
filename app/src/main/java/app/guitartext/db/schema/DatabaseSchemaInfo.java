package app.guitartext.db.schema;

import com.google.common.collect.ImmutableList;

import java.util.List;

import javax.inject.Inject;

import app.guitartext.db.schema.tables.BaseFile;
import app.guitartext.db.schema.tables.OpenedFile;
import app.guitartext.db.schema.tables.User;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

public class DatabaseSchemaInfo
{
	private static final String DATABASE_NAME = "guitar_text.db";
	private static final int DATABASE_VERSION = 2;

	private List<Class<?>> tables;

	@Inject
	public DatabaseSchemaInfo()
	{
		tables = ImmutableList.of(User.class,  OpenedFile.class, BaseFile.class);
	}

	public String getDatabaseName()
	{
		return DATABASE_NAME;
	}

	public int getDatabaseVersion()
	{
		return DATABASE_VERSION;
	}

	public List<Class<?>> getTables()
	{
		return tables;
	}
}
