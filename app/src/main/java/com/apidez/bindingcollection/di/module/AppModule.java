package com.apidez.bindingcollection.di.module;

import android.content.Context;

import com.apidez.bindingcollection.data.repo.TodoRepo;
import com.apidez.bindingcollection.data.repo.TodoRepoImpl;
import com.apidez.bindingcollection.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return context;
    }

    @Provides
    @ApplicationScope
    TodoRepo provideTodoRepo() {
        return new TodoRepoImpl();
    }
}
