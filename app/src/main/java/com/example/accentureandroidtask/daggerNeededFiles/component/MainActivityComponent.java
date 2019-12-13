package com.example.accentureandroidtask.daggerNeededFiles.component;


import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.module.AdapterModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityMvpModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.ui.mainActivityMVP.MainActivity;


import dagger.Component;

@ActivityScope
@Component(modules = {MainActivityContextModule.class, MainActivityMvpModule.class, AdapterModule.class/*, DatabaseModule.class*/},
        dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(MainActivity mainActivity);
}