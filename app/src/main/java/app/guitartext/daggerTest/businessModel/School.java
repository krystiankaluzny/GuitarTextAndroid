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

//School cannot be provided without an @Inject constructor or from an @Provides- or @Produces-annotated method. This type supports members injection but cannot be implicitly provided.
	@Inject
	public School()
	{
	}

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
