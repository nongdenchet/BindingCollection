package com.apidez.bindingcollection.di.module;

import android.app.Activity;
import android.content.Context;

import com.apidez.bindingcollection.di.scope.ActivityScope;
import com.apidez.bindingcollection.di.scope.ForActivity;
import com.apidez.bindingcollection.support.ListBinder;
import com.apidez.bindingcollection.ui.TodoDiffCallback;
import com.apidez.bindingcollection.ui.TodoViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ForActivity
    @ActivityScope
    public Context provideContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    public ListBinder<TodoViewModel> provideTodoListBinder(TodoDiffCallback todoDiffCallback) {
        return new ListBinder<>(todoDiffCallback);
    }
}
