package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class School
{
	@Inject Bus bus;
	@Inject Children children;
	@Inject Principal principal;

	@Override
	public String toString()
	{
		return "School" + hashCode() + "{" +
				"bus=" + bus +
				", children=" + children +
				", principal=" + principal +
				'}';
	}
}
