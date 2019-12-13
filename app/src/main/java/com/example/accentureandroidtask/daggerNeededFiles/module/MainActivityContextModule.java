package com.example.accentureandroidtask.daggerNeededFiles.module;

import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.ui.mainActivityMVP.MainActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityContextModule {


    private MainActivity mainActivity;
    private Context context;

    public MainActivityContextModule(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity(){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}