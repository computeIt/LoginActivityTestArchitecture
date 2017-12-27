package com.sheygam.loginarchitectureexample.business.login;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginInteratorCallback {
    void onSuccess();
    void onError(String error);
}
