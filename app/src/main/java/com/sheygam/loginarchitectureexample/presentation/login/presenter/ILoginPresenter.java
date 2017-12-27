package com.sheygam.loginarchitectureexample.presentation.login.presenter;

import com.sheygam.loginarchitectureexample.presentation.login.view.ILoginView;

/**
 * Created by gregorysheygam on 27/12/2017.
 */

public interface ILoginPresenter {
    void bindView(ILoginView view);
    void unbindView();
    void makeLogin(String email, String password);
    void makeRegistration(String email, String password);
}
