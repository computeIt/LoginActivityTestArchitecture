package com.sheygam.loginarchitectureexample.data.repositories.login.web;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginWebRepositoryCallback {
    void onLoginSuccess(String token);
    void onLoginError(String error);
    void onRegSuccess(String token);
    void onRegError(String error);
}
