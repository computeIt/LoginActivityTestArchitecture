package com.sheygam.loginarchitectureexample.business.login;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginInteractor {

    Completable login(String email, String password) throws PasswordValidException,EmailValidException;
    Completable registration(String email, String password)throws PasswordValidException,EmailValidException;
}
