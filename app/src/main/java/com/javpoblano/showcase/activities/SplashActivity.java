package com.javpoblano.showcase.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.javpoblano.showcase.R;
import com.javpoblano.showcase.utils.SharedPrefs;

public class SplashActivity extends AppCompatActivity {
    SharedPrefs sharedPrefs;
    final int REQUEST_MULTIPLE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        final Activity parent = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(parent,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_MULTIPLE);
                }
                else{
                    startActivity();
                }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults)
    {
        switch (requestCode) {
            case REQUEST_MULTIPLE:
                if (grantResults.length>0)
                {
                    boolean phone = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(phone)
                    {
                        startActivity();
                    }
                    else
                    {
                        finish();
                    }
                }
                else
                {
                    finish();
                }
            break;
        }
    }

}
