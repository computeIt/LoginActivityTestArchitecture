package com.sheygam.loginarchitectureexample.data.repositories.login.web;

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
    public Single<String> registration(String email, String password) {
        Auth auth = new Auth(email,password);
        return loginApi.registration(auth)
                .doOnError(throwable -> {throw new Exception("Connection error!");})
                .map(this::handleRegResponse);
    }

    @Override
    public Single<String> login(String email, String password) {
        Auth auth = new Auth(email,password);
        return loginApi.login(auth)
                .doOnError(throwable -> {throw new Exception("Connection error!");})
                .map(this::handleLoginResponse);
    }

    private String handleRegResponse(Response<AuthToken> response) throws Exception {
        if(response.isSuccessful()){
            return response.body().getToken();
        }else if(response.code() == 409){
            throw new Exception("User already exist!");
        }else{
            throw new Exception("Server error");
        }
    }

    private String handleLoginResponse(Response<AuthToken> response) throws Exception {
        if(response.isSuccessful()){
            return response.body().getToken();
        }else if(response.code() == 401){
            throw new Exception("Wrong email or password!");
        }else{
            throw new Exception("Server error");
        }
    }

}
