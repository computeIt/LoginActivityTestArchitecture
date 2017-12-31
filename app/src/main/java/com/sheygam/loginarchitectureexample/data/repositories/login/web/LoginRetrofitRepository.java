package com.sheygam.loginarchitectureexample.data.repositories.login.web;

import android.util.Log;

import com.sheygam.loginarchitectureexample.data.dao.Auth;
import com.sheygam.loginarchitectureexample.data.dao.AuthToken;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by gregorysheygam on 31/12/2017.
 */

public class LoginRetrofitRepository implements ILoginWebRepository {
    private LoginApi loginApi;

    public LoginRetrofitRepository(LoginApi loginApi) {
        this.loginApi = loginApi;
    }


    @Override
    public Single<Response<AuthToken>> registration(String email, String password) {
        Auth auth = new Auth(email,password);
        return loginApi.registration(auth);
    }

    @Override
    public Single<Response<AuthToken>> login(String email, String password) {
        Auth auth = new Auth(email,password);
        return loginApi.login(auth);
    }
}
