package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Singleton
public class Bus extends Car
{
	@Inject TicketMachine ticketMachine;

	@Inject
	public Bus()
	{
	}

	@Override
	public String toString()
	{
		return "Bus" + hashCode() + "{" +
				"ticketMachine=" + ticketMachine +
				"} " + super.toString();
	}
}
