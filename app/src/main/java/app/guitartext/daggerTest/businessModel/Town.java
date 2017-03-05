package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;

import app.guitartext.daggerTest.TownComponent;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class Town
{
	@Inject School school1;
	@Inject School school2;
	@Inject School school3;

	public Town(TownComponent townComponent)
	{
		townComponent.inject(this);
	}

	@Override
	public String toString()
	{
		return "Town" + hashCode() + "{" +
				"school1=" + school1 +
				", school2=" + school2 +
				", school3=" + school3 +
				'}';
	}
}
