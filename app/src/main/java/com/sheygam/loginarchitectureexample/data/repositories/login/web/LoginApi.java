package com.sheygam.loginarchitectureexample.data.repositories.login.web;

import com.sheygam.loginarchitectureexample.data.dao.Auth;
import com.sheygam.loginarchitectureexample.data.dao.AuthToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by gregorysheygam on 31/12/2017.
 */

public interface LoginApi {
    @POST("_ah/api/contactsApi/v1/login")
    Call<AuthToken> login(@Body Auth auth);

    @POST("_ah/api/contactsApi/v1/registration")
    Call<AuthToken> registration(@Body Auth auth);
}
