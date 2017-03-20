package com.apidez.bindingcollection.di.component;

import com.apidez.bindingcollection.di.module.ActivityModule;
import com.apidez.bindingcollection.di.scope.ActivityScope;
import com.apidez.bindingcollection.ui.TodoListActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(TodoListActivity todoListActivity);
}
