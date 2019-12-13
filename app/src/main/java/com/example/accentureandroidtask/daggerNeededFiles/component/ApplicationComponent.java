package com.example.accentureandroidtask.daggerNeededFiles.component;

import android.content.Context;

import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.daggerNeededFiles.module.ContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.DatabaseModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.DatabaseInfo;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ApplicationScope;
import com.example.accentureandroidtask.daggerNeededFiles.module.RetrofitModule;
import com.example.accentureandroidtask.roomdatabase.AppDatabase;
import com.example.accentureandroidtask.root.MyApplication;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class,DatabaseModule.class,})
public interface ApplicationComponent {

    APIInterface getApiInterface();
    AppDatabase getDataBase();

    @ApplicationContext
    Context getContext();

    @DatabaseInfo
    String provideDatabaseName();

    void injectApplication(MyApplication application);
}