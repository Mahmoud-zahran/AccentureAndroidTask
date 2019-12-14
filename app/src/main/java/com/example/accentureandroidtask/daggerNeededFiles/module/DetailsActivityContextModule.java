package com.example.accentureandroidtask.daggerNeededFiles.module;

import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.ui.detailsActivityMVP.DetailsActivity;
import com.example.accentureandroidtask.ui.mainActivityMVP.MainActivity;

import dagger.Module;
import dagger.Provides;


@Module
public class DetailsActivityContextModule {


    private DetailsActivity detailsActivity;
    private Context context;

    public DetailsActivityContextModule(DetailsActivity detailsActivity){
        this.detailsActivity = detailsActivity;
        context = detailsActivity;
    }

    @Provides
    @ActivityScope
    public DetailsActivity providesDetailsActivity(){
        return detailsActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}