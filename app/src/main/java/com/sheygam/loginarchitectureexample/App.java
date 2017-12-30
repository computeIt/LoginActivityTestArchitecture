package com.sheygam.loginarchitectureexample;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.sheygam.loginarchitectureexample.di.application.AppComponent;
import com.sheygam.loginarchitectureexample.di.application.AppModule;
import com.sheygam.loginarchitectureexample.di.application.DaggerAppComponent;

/**
 * Created by gregorysheygam on 30/12/2017.
 */

public class App extends Application {
    private AppComponent component;

    public static App get(@NonNull Context context){
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = prepareComponent();
    }

    private AppComponent prepareComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent applicationComponent(){
        return component;
    }
}
