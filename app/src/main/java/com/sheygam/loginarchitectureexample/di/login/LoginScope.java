package com.sheygam.loginarchitectureexample.di.login;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by gregorysheygam on 30/12/2017.
 */

@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LoginScope {
}
