package com.sheygam.loginarchitectureexample.data.repositories.login.web;

import io.reactivex.Single;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginWebRepository {
    Single<String> registration(String email, String password);
    Single<String> login(String email, String password);
}
