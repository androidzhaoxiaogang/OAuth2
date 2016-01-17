package it.seiton.oauth2.common.oauth2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by lukasw44 on 17.01.16.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Oauth2 {
}
