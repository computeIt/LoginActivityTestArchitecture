package com.sheygam.loginarchitectureexample.data.repositories.login.web;

import android.util.Log;

import com.sheygam.loginarchitectureexample.data.dao.Auth;
import com.sheygam.loginarchitectureexample.data.dao.AuthToken;

import retrofit2.Call;
import retrofit2.Callback;
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
    public void registration(String email, String password, final ILoginWebRepositoryCallback callback) {
        Auth auth = new Auth(email,password);
        loginApi.registration(auth).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if(response.code() < 400) {
                    callback.onRegSuccess(response.body().getToken());
                }else if(response.code() == 409){
                    callback.onRegError("User already exist!");
                }else{
                    callback.onRegError("Server error!");
                    Log.d("MY_TAG", "onResponse: error:" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                callback.onRegError("Connection error!");
                t.printStackTrace();

            }
        });
    }

    @Override
    public void login(String email, String password, final ILoginWebRepositoryCallback callback) {
        Auth auth = new Auth(email,password);
        loginApi.login(auth).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if(response.code() < 400) {
                    callback.onLoginSuccess(response.body().getToken());
                }else if(response.code() == 401){
                    callback.onLoginError("Wrong login or password");
                }else{
                    callback.onLoginError("Server error!");
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                callback.onLoginError("Connection error!");
                t.printStackTrace();

            }
        });
    }
}
