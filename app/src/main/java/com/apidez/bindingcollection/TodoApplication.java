package com.apidez.bindingcollection;

import android.app.Application;

import com.apidez.bindingcollection.di.component.AppComponent;
import com.apidez.bindingcollection.di.component.DaggerAppComponent;
import com.apidez.bindingcollection.di.module.AppModule;

public class TodoApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    private void buildComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent component() {
        return appComponent;
    }
}
