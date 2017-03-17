package app.guitartext.db.dao;

import com.j256.ormlite.dao.Dao;

/**
 * Created by obywatel on 17.03.2017.
 * Modified by
 */

public interface DaoFactory
{
	<D extends Dao<T, ?>, T> D getDao(Class<T> clazz);
}
