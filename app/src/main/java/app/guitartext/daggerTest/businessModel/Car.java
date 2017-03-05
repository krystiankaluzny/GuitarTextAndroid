package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public abstract class Car
{
	@Inject Wheel wheel1;
	@Inject Wheel wheel2;
	@Inject Wheel wheel3;
	@Inject Wheel wheel4;

	// @Inject is nonsense on the constructor of an abstract class
//	@Inject
//	public Car()
//	{
//	}

	@Override
	public String toString()
	{
		return "Car" + hashCode() + "{" +
				"wheel1=" + wheel1 +
				", wheel2=" + wheel2 +
				", wheel3=" + wheel3 +
				", wheel4=" + wheel4 +
				'}';
	}
}
