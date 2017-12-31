package com.sheygam.loginarchitectureexample.business.login;

import com.sheygam.loginarchitectureexample.data.dao.AuthToken;
import com.sheygam.loginarchitectureexample.data.repositories.login.prefstore.ILoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.ILoginWebRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    public void login(String email, String password, ILoginInteratorCallback callback) throws PasswordValidException, EmailValidException {
        if(!isEmailValid(email)){
            throw new EmailValidException();
        }
        if(!isPasswordValid(password)){
            throw new PasswordValidException();
        }

        webRepository.login(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response.isSuccessful()){
                        handleSuccess(response.body());
                        callback.onSuccess();
                    }else if(response.code() == 401){
                        callback.onError("Wrong email or password!");
                    }else{
                        callback.onError("Server error");
                    }
                }, throwable -> callback.onError("Server error"));
    }

    @Override
    public void registration(String email, String password, ILoginInteratorCallback callback) throws PasswordValidException, EmailValidException {
        if(!isEmailValid(email)){
            throw new EmailValidException();
        }
        if(!isPasswordValid(password)){
            throw new PasswordValidException();
        }

        webRepository.registration(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response.isSuccessful()){
                        handleSuccess(response.body());
                        callback.onSuccess();
                    }else if(response.code() == 409){
                        callback.onError("User already exist!");
                    }else{
                        callback.onError("Server error");
                    }
                }, throwable -> callback.onError("Server error"));
    }

    private void handleSuccess(AuthToken token){
        storeRepository.saveToken(token.getToken());
    }

}
