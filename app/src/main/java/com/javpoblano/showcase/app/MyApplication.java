package com.javpoblano.showcase.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by javpoblano on 12/9/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        //Inicializar realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("showcase.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
