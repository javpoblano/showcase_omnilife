package com.javpoblano.showcase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.interfaces.LoginPresenterInterface;
import com.javpoblano.showcase.models.ws.LoginResponse;
import com.javpoblano.showcase.presenters.LoginPresenter;
import com.javpoblano.showcase.utils.SharedPrefs;

public class LoginActivity extends AppCompatActivity implements LoginPresenterInterface{
    SharedPrefs sharedPrefs;
    TextInputEditText user,pass;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (TextInputEditText)findViewById(R.id.et_usuario);
        pass = (TextInputEditText)findViewById(R.id.et_pass);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        loginPresenter = new LoginPresenter(getApplicationContext(),this);
    }

    public void login(View view)
    {
        loginPresenter.attemptLogin(user.getText().toString(),pass.getText().toString(),"MEX");
    }

    @Override
    public void onLoadFinished(LoginResponse loginResponse) {
        if(loginResponse.getSuccess())
        {
            sharedPrefs.saveSharedSetting("session",true);
            sharedPrefs.saveSharedSetting("name",loginResponse.getData().getName());
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Usuario / Contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
