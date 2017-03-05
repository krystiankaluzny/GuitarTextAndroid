package app.guitartext.daggerTest.businessModel;

import javax.inject.Named;
import javax.inject.Singleton;

import app.guitartext.daggerTest.MainClass;
import dagger.Module;
import dagger.Provides;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Module
public class SchoolModule
{
	MainClass.Context context;

	public SchoolModule(MainClass.Context context)
	{
		this.context = context;
	}

	@Provides
	@Singleton
	MainClass.Context provideContext()
	{
		return context;
	}

	@Provides
	Children provideChildren(MainClass.Context context)
	{
		return new Children(context);
	}

	@Provides
	Principal providePrincipal(@Named("imie_dyrektora") String name)
	{
		return new Principal(name);
	}

	@Provides
	@Named("imie_dyrektora")
	String providePrincipalName()
	{
		return "Janusz";
	}

	//DO NOT CREATE PROVIDE METHODS FOR OBJECTS WITH EMPTY CONSTRUCTOR CAUSE INNER FIELDS WILL NOT BE INJECTED
//	@Provides
//	@Singleton
//	Bus provideBus()
//	{
//		return new Bus();
//	}

//	@Provides
//	TicketMachine provideTicketMachine()
//	{
//		return new TicketMachine();
//	}
//
//	@Provides
//	Wheel provideWheel()
//	{
//		return new Wheel();
//	}

//	@Provides
//	School provideSchool()
//	{
//		return new School();
//	}
}
