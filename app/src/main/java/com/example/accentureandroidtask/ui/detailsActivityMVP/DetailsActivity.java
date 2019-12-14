package com.example.accentureandroidtask.ui.detailsActivityMVP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerDetailsActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DetailsActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.DetailsActivityContextModule;

import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;

import com.example.accentureandroidtask.root.MyApplication;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity {
    DetailsActivityComponent detailsActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ActivityContext
    public Context mContext;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView = findViewById(R.id.textView);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        detailsActivityComponent = DaggerDetailsActivityComponent.builder()
                .detailsActivityContextModule(new DetailsActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        detailsActivityComponent.injectDetailsActivity(this);
        textView.setText(url);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(mContext);
    }
}
