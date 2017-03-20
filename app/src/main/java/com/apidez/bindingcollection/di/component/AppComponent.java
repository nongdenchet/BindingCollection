package com.apidez.bindingcollection.di.component;

import com.apidez.bindingcollection.di.module.ActivityModule;
import com.apidez.bindingcollection.di.module.AppModule;
import com.apidez.bindingcollection.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule activityModule);
}
