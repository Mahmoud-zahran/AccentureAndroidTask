package com.example.accentureandroidtask.daggerNeededFiles.module;

import com.example.accentureandroidtask.adapter.RecyclerViewAdapter;
import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.ui.mainActivityMVP.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getStarWarsPeopleLIst(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return  mainActivity;
    }
}
