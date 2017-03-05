package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class Wheel
{
	@Inject
	public Wheel()
	{
	}

	@Override
	public String toString()
	{
		return "Wheel" + hashCode() + "{}";
	}
}
