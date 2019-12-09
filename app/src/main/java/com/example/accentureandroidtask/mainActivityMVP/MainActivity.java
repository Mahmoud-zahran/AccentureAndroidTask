package com.example.accentureandroidtask.mainActivityMVP;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerMainActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.MainActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityMvpModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.entity.Response;
import com.example.accentureandroidtask.root.MyApplication;
import com.example.accentureandroidtask.testCases.TestingActivity;
import com.example.accentureandroidtask.util.CustomProgressDialog;

import java.util.ArrayList;

import javax.inject.Inject;


import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    @Inject
    @ApplicationContext
    public Context context;

    @Inject
    @ActivityContext
    public Context activityContext;
    String TAG = "MainActivity";

    @Inject
    MainActivityPresenterImpl mainActivityPresenter;
    CustomProgressDialog mCustomProgressDialog;
    private ArrayList<Response> users;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent mainActivityComponent;


        ButterKnife.bind(this);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);

        mainActivityPresenter.loadFeedsData();

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_SetVehicle:
                Intent intent = new Intent(MainActivity.this, TestingActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void showError(String call, String statusMessage) {
        if (call.equals("network error")) {
            Log.d(TAG, "showError:network error " + statusMessage);
            Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "showError: " + "error message");
            Toast.makeText(context, "error message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        mCustomProgressDialog = new CustomProgressDialog(this);
        mCustomProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mCustomProgressDialog.show();
        Log.d(TAG, "showProgress: " + "showProgress message");
    }

    @Override
    public void hideProgress() {
        mCustomProgressDialog.dismiss();
        mCustomProgressDialog = null;
        Log.d(TAG, "hideProgress: " + "hideProgress message");
    }

    @Override
    public void showComplete() {
        users = mainActivityPresenter.getUsers();
        Log.d(TAG, "showComplete: " + "showComplete message");
    }

    @Override
    public void updateRecycleView(ArrayList<Response> users) {

    }
}
