package com.sheygam.loginarchitectureexample.business.login;

import com.sheygam.loginarchitectureexample.data.repositories.login.prefstore.ILoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.ILoginWebRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.ILoginWebRepositoryCallback;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public class LoginInteractor implements ILoginInteractor, ILoginWebRepositoryCallback {
    private ILoginWebRepository webRepository;
    private ILoginStoreRepository storeRepository;
    private ILoginInteratorCallback loginCallback;
    private ILoginInteratorCallback regCallback;

    public LoginInteractor(ILoginWebRepository webRepository, ILoginStoreRepository storeRepository) {
        this.webRepository = webRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public void login(String email, String password, ILoginInteratorCallback callback) throws PasswordValidException, EmailValidException {
        if(!isEmailValid(email)){
            throw new EmailValidException();
        }
        if(!isPasswordValid(password)){
            throw new PasswordValidException();
        }
        loginCallback = callback;
        webRepository.login(email,password,this);

    }

    @Override
    public void registration(String email, String password, ILoginInteratorCallback callback) throws PasswordValidException, EmailValidException {
        if(!isEmailValid(email)){
            throw new EmailValidException();
        }
        if(!isPasswordValid(password)){
            throw new PasswordValidException();
        }
        regCallback = callback;
        webRepository.registration(email,password,this);
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() >= 4;
    }

    @Override
    public void onLoginSuccess(String token) {
        storeRepository.saveToken(token);
        loginCallback.onSuccess();
    }

    @Override
    public void onLoginError(String error) {
        loginCallback.onError(error);
    }

    @Override
    public void onRegSuccess(String token) {
        storeRepository.saveToken(token);
        regCallback.onSuccess();
    }

    @Override
    public void onRegError(String error) {
        regCallback.onError(error);
    }
}
