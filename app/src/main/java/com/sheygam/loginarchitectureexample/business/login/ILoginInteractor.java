package com.sheygam.loginarchitectureexample.business.login;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginInteractor {

    void login(String email, String password, ILoginInteratorCallback callback) throws PasswordValidException,EmailValidException;
    void registration(String email, String password, ILoginInteratorCallback callback)throws PasswordValidException,EmailValidException;
}
