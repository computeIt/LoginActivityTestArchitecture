package com.sheygam.loginarchitectureexample.di.login;

import android.content.Context;

import com.sheygam.loginarchitectureexample.business.login.ILoginInteractor;
import com.sheygam.loginarchitectureexample.business.login.LoginInteractor;
import com.sheygam.loginarchitectureexample.data.repositories.login.prefstore.ILoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.ILoginWebRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.prefstore.LoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.LoginApi;
import com.sheygam.loginarchitectureexample.data.repositories.login.web.LoginRetrofitRepository;

import com.sheygam.loginarchitectureexample.presentation.login.presenter.ILoginPresenter;
import com.sheygam.loginarchitectureexample.presentation.login.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by gregorysheygam on 30/12/2017.
 */
@Module
public class LoginModule {


    @Provides
    @LoginScope
    ILoginWebRepository provideWebRepository(Retrofit retrofit){
        return new LoginRetrofitRepository(retrofit.create(LoginApi.class));
    }

    @Provides
    @LoginScope
    ILoginStoreRepository provideStroreRepository(Context context){
        return new LoginStoreRepository(context);
    }

    @Provides
    @LoginScope
    ILoginInteractor provideLoginInteractor(ILoginWebRepository webRepository, ILoginStoreRepository storeRepository){
        return new LoginInteractor(webRepository,storeRepository);
    }

    @Provides
    @LoginScope
    ILoginPresenter provideLoginPresenter(ILoginInteractor interactor){
        return new LoginPresenter(interactor);
    }
}
