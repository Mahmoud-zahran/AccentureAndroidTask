package com.example.accentureandroidtask.root;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.ContextModule;

public class MyApplication extends Application {
    ApplicationComponent applicationComponent;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        dependencyInjection();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MyApplication get(Activity activity) {

      //  return getApplicationContext();
          return (MyApplication) activity.getApplication();
    }
    public static Context getContext(){
        return mContext;
    }

    private void dependencyInjection(){
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }
}