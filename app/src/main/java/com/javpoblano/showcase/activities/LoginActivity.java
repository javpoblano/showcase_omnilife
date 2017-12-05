package com.javpoblano.showcase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.utils.SharedPrefs;

public class LoginActivity extends AppCompatActivity {
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPrefs = new SharedPrefs(getApplicationContext());
    }

    public void login(View view)
    {
        sharedPrefs.saveSharedSetting("session",true);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}
