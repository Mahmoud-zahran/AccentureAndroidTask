package com.example.accentureandroidtask.ui.detailsActivityMVP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerDetailsActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DetailsActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.root.MyApplication;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity {
    DetailsActivityComponent detailsActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
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
                .applicationComponent(applicationComponent)
                .build();

        detailsActivityComponent.inject(this);
        textView.setText(url);

    }
}
