package app.guitartext.daggerTest.businessModel;

import javax.inject.Inject;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class Bus extends Car
{
	@Inject TicketMachine ticketMachine;

	@Override
	public String toString()
	{
		return "Bus" + hashCode() + "{" +
				"ticketMachine=" + ticketMachine +
				"} " + super.toString();
	}
}
