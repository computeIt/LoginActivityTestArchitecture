package com.sheygam.loginarchitectureexample.data;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginWebRepositoryCallback {
    void onLoginSuccess(String token);
    void onLoginError(String error);
    void onRegSuccess(String token);
    void onRegError(String error);
}
