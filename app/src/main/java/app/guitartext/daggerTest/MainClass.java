package app.guitartext.daggerTest;

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
//		TownComponent townComponent = DaggerTownComponent.builder()
//				.schoolModule(new SchoolModule(context))
//				.build();
//
//		Town niggaSmurfsVillage = new Town(townComponent);
//		Log.d("test", niggaSmurfsVillage.toString());
	}
}
