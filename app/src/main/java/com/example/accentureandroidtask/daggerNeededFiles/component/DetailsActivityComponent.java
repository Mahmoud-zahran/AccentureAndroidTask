package com.example.accentureandroidtask.daggerNeededFiles.component;

import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.module.DetailsActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.ui.detailsActivityMVP.DetailsActivity;
import com.example.accentureandroidtask.ui.mainActivityMVP.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = {DetailsActivityContextModule.class}
,dependencies = ApplicationComponent.class)
public interface DetailsActivityComponent {

    @ActivityContext
    Context getContext();
    void injectDetailsActivity(DetailsActivity detailsActivity);
}