package app.guitartext.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by obywatel on 06.03.2017.
 * Modified by
 */

@Scope
@Retention(RUNTIME)
public @interface ApplicationScope
{
}
