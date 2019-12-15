package com.example.accentureandroidtask.daggerNeededFiles.module;

import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.testCases.TestingActivity;
import com.example.accentureandroidtask.ui.detailsActivityMVP.DetailsActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class TestingActivityContextModule {


    private TestingActivity testingActivity;
    private Context context;

    public TestingActivityContextModule(TestingActivity testingActivity){
        this.testingActivity = testingActivity;
        context = testingActivity;
    }

    @Provides
    @ActivityScope
    public TestingActivity providesTestingActivity(){
        return testingActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}