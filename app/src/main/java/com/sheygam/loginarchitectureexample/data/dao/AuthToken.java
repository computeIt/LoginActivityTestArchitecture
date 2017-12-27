package com.sheygam.loginarchitectureexample.data.dao;

/**
 * Created by gregorysheygam on 28/12/2017.
 */

public class AuthToken {
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
