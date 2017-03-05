package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;

import app.guitartext.daggerTest.MainClass;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class Children
{
	private MainClass.Context context;

	@Inject
	public Children(MainClass.Context context)
	{
		this.context = context;
	}

	@Override
	public String toString()
	{
		return "Children" + hashCode() + "{" +
				"context=" + context +
				'}';
	}
}
