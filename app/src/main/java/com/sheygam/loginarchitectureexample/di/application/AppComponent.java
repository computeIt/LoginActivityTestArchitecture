package com.sheygam.loginarchitectureexample.di.application;

import com.sheygam.loginarchitectureexample.di.login.LoginComponent;
import com.sheygam.loginarchitectureexample.di.login.LoginModule;

import dagger.Component;

/**
 * Created by gregorysheygam on 30/12/2017.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {
    LoginComponent plus(LoginModule loginModule);
}
