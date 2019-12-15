package com.example.accentureandroidtask.daggerNeededFiles.component;


import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.module.AdapterModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.DetailsActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityMvpModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.RetrofitModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.TestingActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.testCases.TestingActivity;
import com.example.accentureandroidtask.ui.mainActivityMVP.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = {TestingActivityContextModule.class, }
        ,dependencies = ApplicationComponent.class)
public interface TestingActivityComponent {

    @ActivityContext
    Context getContext();
    void injectTestingActivity(TestingActivity testingActivity);
}