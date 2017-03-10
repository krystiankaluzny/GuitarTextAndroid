package app.guitartext.dagger.activity;

import app.guitartext.dagger.scopes.ActivityScope;
import app.guitartext.ui.text.TextActivity;
import dagger.Subcomponent;

/**
 * Created by obywatel on 09.03.2017.
 * Modified by
 */

@ActivityScope
@Subcomponent(modules = TextModule.class)
public interface TextComponent
{
	void inject(TextActivity textActivity);
}
