package app.guitartext.ui.text.presenter;

import app.guitartext.scopes.ActivityScope;
import app.guitartext.ui.text.TextActivity;
import app.guitartext.ui.text.component.TextModule;
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
