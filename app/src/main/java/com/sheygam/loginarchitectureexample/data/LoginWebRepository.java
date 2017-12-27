package com.sheygam.loginarchitectureexample.data;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.sheygam.loginarchitectureexample.data.dao.Auth;
import com.sheygam.loginarchitectureexample.data.dao.AuthToken;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public class LoginWebRepository implements ILoginWebRepository{
    public static final String BASE_URL = "https://telranstudentsproject.appspot.com/_ah/api/contactsApi/v1";
    private Gson gson;
    private OkHttpClient client;

    public LoginWebRepository(Gson gson, OkHttpClient client) {
        this.gson = gson;
        this.client = client;
    }

    @Override
    public void registration(String email, String password, ILoginWebRepositoryCallback callback) {
        new RegTask(email,password,callback).execute();
    }

    @Override
    public void login(String email, String password, ILoginWebRepositoryCallback callback) {
        new LoginTask(email,password,callback).execute();
    }

    private class LoginTask extends AsyncTask<Void,Void,Void>{
        private String email, password;
        private ILoginWebRepositoryCallback callback;

        public LoginTask(String email, String password, ILoginWebRepositoryCallback callback) {
            this.email = email;
            this.password = password;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Auth auth = new Auth(email,password);
            String data = gson.toJson(auth);
            MediaType json = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json,data);
            Request request = new Request.Builder()
                    .url(BASE_URL + "/login")
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if(response.code() < 400){
                    String jsonBody = response.body().string();
                    Log.d("MY_TAG", "login doInBackground server response:" + jsonBody);
                    AuthToken token = gson.fromJson(jsonBody,AuthToken.class);
                    callback.onLoginSuccess(token.getToken());
                }else if(response.code() == 401){
                    callback.onLoginError("Wrong email or password!");
                }else{
                    callback.onLoginError("Server error!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onLoginError("Connection error!");
            }
            return null;
        }
    }

    private class RegTask extends AsyncTask<Void,Void,Void>{
        private String email, password;
        private ILoginWebRepositoryCallback callback;

        public RegTask(String email, String password, ILoginWebRepositoryCallback callback) {
            this.email = email;
            this.password = password;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Auth auth = new Auth(email,password);
            String data = gson.toJson(auth);
            MediaType json = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(json,data);
            Request request = new Request.Builder()
                    .url(BASE_URL + "/registration")
                    .post(body)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if(response.code() < 400){
                    String jsonBody = response.body().string();
                    Log.d("MY_TAG", "registration doInBackground server response:" + jsonBody);
                    AuthToken token = gson.fromJson(jsonBody,AuthToken.class);
                    callback.onRegSuccess(token.getToken());
                }else if(response.code() == 409){
                    callback.onRegError("User already exist!");
                }else{
                    callback.onRegError("Server error!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onRegError("Connection error!");
            }
            return null;
        }
    }
}
