package app.guitartext.dagger.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by obywatel on 05.03.2017.
 * Modified by
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope
{
}
