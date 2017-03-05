package app.guitartext.daggerTest;

import android.util.Log;

import app.guitartext.daggerTest.businessModel.SchoolModule;
import app.guitartext.daggerTest.businessModel.Town;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

public class MainClass
{
	private Context context;

	public enum Race
	{
		Negroid, //czarny
		Caucasoid, //biała
		Mongoloid //żółty
	}

	public static class Context
	{
		Race race;

		public Context(Race race)
		{
			this.race = race;
		}
	}

	public MainClass()
	{
		context = new Context(Race.Negroid);
//		DaggerTownComponent
		TownComponent townComponent = DaggerTownComponent.builder()
				.schoolModule(new SchoolModule(context))
				.build();

		Town niggaSmurfsVillage = new Town(townComponent);
		Log.d("test", niggaSmurfsVillage.toString());
	}
}
