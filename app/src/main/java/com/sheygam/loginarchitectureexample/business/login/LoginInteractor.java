package com.sheygam.loginarchitectureexample.business.login;

import com.sheygam.loginarchitectureexample.data.repositories.login.prefstore.ILoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.ILoginWebRepository;

import io.reactivex.Completable;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public class LoginInteractor implements ILoginInteractor {
    private ILoginWebRepository webRepository;
    private ILoginStoreRepository storeRepository;

    public LoginInteractor(ILoginWebRepository webRepository, ILoginStoreRepository storeRepository) {
        this.webRepository = webRepository;
        this.storeRepository = storeRepository;
    }


    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() >= 4;
    }

    @Override
    public Completable login(String email, String password) throws PasswordValidException, EmailValidException {
        if(!isEmailValid(email)){
            throw new EmailValidException();
        }
        if(!isPasswordValid(password)){
            throw new PasswordValidException();
        }

        return webRepository.login(email,password).doOnSuccess(this::saveToPref).toCompletable();
    }

    @Override
    public Completable registration(String email, String password) throws PasswordValidException, EmailValidException {
        if(!isEmailValid(email)){
            throw new EmailValidException();
        }
        if(!isPasswordValid(password)){
            throw new PasswordValidException();
        }

        return webRepository.registration(email,password).doOnSuccess(this::saveToPref).toCompletable();
    }

    private void saveToPref(String s) {
        storeRepository.saveToken(s);
    }

}
