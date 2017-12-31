package com.sheygam.loginarchitectureexample.data.repositories.login.web;

import com.sheygam.loginarchitectureexample.data.dao.AuthToken;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginWebRepository {
    Single<Response<AuthToken>> registration(String email, String password);
    Single<Response<AuthToken>> login(String email, String password);
}
