package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class Principal
{
	private String name;

	@Inject
	public Principal(@Named("imie_dyrektora") String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Principal" + hashCode() + "{" +
				"name='" + name + '\'' +
				'}';
	}
}
