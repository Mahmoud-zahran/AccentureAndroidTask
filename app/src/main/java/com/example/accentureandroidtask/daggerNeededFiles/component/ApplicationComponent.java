package com.example.accentureandroidtask.daggerNeededFiles.component;

import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.module.ContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ApplicationScope;
import com.example.accentureandroidtask.root.MyApplication;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class})
public interface ApplicationComponent {


    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication application);
}