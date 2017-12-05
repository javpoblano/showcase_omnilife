package com.javpoblano.showcase.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.utils.SharedPrefs;

public class SplashActivity extends AppCompatActivity {
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        }, 500);
    }

    private void startActivity()
    {
        boolean session = sharedPrefs.readSharedSetting("session",false);
        if(!session)
            startActivity(new Intent(this,LoginActivity.class));
        else
            startActivity(new Intent(this,MainActivity.class));
    }
}
