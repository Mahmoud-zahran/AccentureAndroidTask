package com.example.accentureandroidtask.daggerNeededFiles.component;

import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.ui.detailsActivityMVP.DetailsActivity;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailsActivityComponent {

    void inject(DetailsActivity detailActivity);
}