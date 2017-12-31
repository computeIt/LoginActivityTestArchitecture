package com.sheygam.loginarchitectureexample.presentation.login.presenter;

import android.os.Handler;

import com.sheygam.loginarchitectureexample.business.login.EmailValidException;
import com.sheygam.loginarchitectureexample.business.login.ILoginInteractor;
import com.sheygam.loginarchitectureexample.business.login.ILoginInteratorCallback;
import com.sheygam.loginarchitectureexample.business.login.PasswordValidException;
import com.sheygam.loginarchitectureexample.presentation.login.view.ILoginView;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public class LoginPresenter implements ILoginPresenter, ILoginInteratorCallback {
    private ILoginView iLoginView;
    private ILoginInteractor interactor;


    public LoginPresenter(ILoginInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void bindView(ILoginView view) {
        iLoginView = view;
    }

    @Override
    public void unbindView() {
        iLoginView = null;
    }

    @Override
    public void makeLogin(String email, String password) {
        try {
            interactor.login(email, password, this);
            iLoginView.showProgress();
        } catch (PasswordValidException e) {
            iLoginView.invalidPassword();
        } catch (EmailValidException e) {
            iLoginView.invalidEmail();
        }
    }

    @Override
    public void makeRegistration(String email, String password) {
        try {
            interactor.registration(email, password, this);
            iLoginView.showProgress();
        } catch (PasswordValidException e) {
            iLoginView.invalidPassword();
        } catch (EmailValidException e) {
            iLoginView.invalidEmail();
        }
    }

    @Override
    public void onSuccess() {

        if (iLoginView != null) {
            iLoginView.hideProgress();
            iLoginView.showSuccess("Status OK!");
        }

    }

    @Override
    public void onError(final String error) {

        if (iLoginView != null) {
            iLoginView.hideProgress();
            iLoginView.showError(error);
        }

    }
}
