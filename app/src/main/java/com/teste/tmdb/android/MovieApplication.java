package com.teste.tmdb.android;

import android.app.Application;
import android.support.annotation.NonNull;


public class MovieApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
