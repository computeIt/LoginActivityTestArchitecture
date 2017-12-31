package com.sheygam.loginarchitectureexample.data.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public class AuthToken {
    @SerializedName("token")
    @Expose
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
