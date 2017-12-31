package com.sheygam.loginarchitectureexample.presentation.login.view;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sheygam.loginarchitectureexample.App;
import com.sheygam.loginarchitectureexample.R;
import com.sheygam.loginarchitectureexample.business.login.LoginInteractor;
import com.sheygam.loginarchitectureexample.data.LoginStoreRepository;
import com.sheygam.loginarchitectureexample.data.LoginWebRepository;
import com.sheygam.loginarchitectureexample.di.login.LoginModule;
import com.sheygam.loginarchitectureexample.presentation.login.presenter.ILoginPresenter;
import com.sheygam.loginarchitectureexample.presentation.login.presenter.LoginPresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    @Inject
    ILoginPresenter presenter;

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button loginBtn, registrationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Handler handler = new Handler();
//        Gson gson = new Gson();
//        OkHttpClient client = new OkHttpClient();
//        LoginWebRepository webRepository = new LoginWebRepository(gson,client);
//        LoginStoreRepository loginStoreRepository = new LoginStoreRepository(this);
//        LoginInteractor interactor = new LoginInteractor(webRepository, loginStoreRepository);
//        presenter = new LoginPresenter(interactor,handler);

        App.get(this).plusLoginModule(new LoginModule()).inject(this);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        progressBar = findViewById(R.id.myProgress);
        loginBtn = findViewById(R.id.loginBtn);
        registrationBtn = findViewById(R.id.regBtn);
        loginBtn.setOnClickListener(this);
        registrationBtn.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        presenter.bindView(this);
        super.onResume();
    }

    @Override
    public void showProgress() {
        inputPassword.setVisibility(View.INVISIBLE);
        inputEmail.setVisibility(View.INVISIBLE);
        loginBtn.setVisibility(View.INVISIBLE);
        registrationBtn.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        inputPassword.setVisibility(View.VISIBLE);
        inputEmail.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        registrationBtn.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        new AlertDialog.Builder(this)
                .setTitle("Error!")
                .setMessage(error)
                .setPositiveButton("Ok",null)
                .create()
                .show();
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void invalidPassword() {
        inputPassword.setError("Password length needs bigger 4 symbols!");
    }

    @Override
    public void invalidEmail() {
        inputEmail.setError("Email needs contains @!");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            resetInputErrors();
            presenter.makeLogin(inputEmail.getText().toString(),inputPassword.getText().toString());
        }else if(v.getId() == R.id.regBtn){
            resetInputErrors();
            presenter.makeRegistration(inputEmail.getText().toString(),inputPassword.getText().toString());
        }
    }

    private void resetInputErrors(){
        inputEmail.setError(null);
        inputPassword.setError(null);
    }

    @Override
    protected void onStop() {
        presenter.unbindView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        App.get(this).clearLoginComponent();
        super.onDestroy();
    }
}
