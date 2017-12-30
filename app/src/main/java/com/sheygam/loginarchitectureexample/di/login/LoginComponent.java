package com.sheygam.loginarchitectureexample.di.login;

import com.sheygam.loginarchitectureexample.presentation.login.view.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by gregorysheygam on 30/12/2017.
 */
@Subcomponent(modules = {LoginModule.class})
@LoginScope
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
