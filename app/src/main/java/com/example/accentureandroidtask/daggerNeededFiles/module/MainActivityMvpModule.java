package com.example.accentureandroidtask.daggerNeededFiles.module;



import com.example.accentureandroidtask.daggerNeededFiles.scope.ActivityScope;
import com.example.accentureandroidtask.mainActivityMVP.MainActivityContract;
import com.example.accentureandroidtask.mainActivityMVP.MainActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {

    private MainActivityContract.View mView;

    public MainActivityMvpModule(MainActivityContract.View mView){
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.View provideView(){
        return mView;
    }

    @Provides
    @ActivityScope
    MainActivityPresenterImpl providePresenter(MainActivityContract.View mView){
        return new MainActivityPresenterImpl( mView);
    }

}
