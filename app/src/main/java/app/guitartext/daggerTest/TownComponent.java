package app.guitartext.daggerTest;

import javax.inject.Singleton;

import app.guitartext.daggerTest.businessModel.SchoolModule;
import app.guitartext.daggerTest.businessModel.Town;
import dagger.Component;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */
@Singleton
@Component(modules = { SchoolModule.class })
public interface TownComponent
{
	void inject(Town town);
}
