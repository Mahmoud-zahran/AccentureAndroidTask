package com.example.accentureandroidtask.ui.detailsActivityMVP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerDetailsActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DetailsActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.DetailsActivityContextModule;

import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;

import com.example.accentureandroidtask.root.MyApplication;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsActivity extends AppCompatActivity {
    DetailsActivityComponent detailsActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ActivityContext
    public Context mContext;

    TextView cityText;
    TextView tempText;
    TextView humidityText;
    TextView windText;
    TextView descriptionText;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cityText = findViewById(R.id.city);
        tempText = findViewById(R.id.temp);
        humidityText = findViewById(R.id.humidity);
        windText = findViewById(R.id.wind);
        descriptionText = findViewById(R.id.description);
        animationView= findViewById(R.id.animation_view);

        String city = getIntent().getStringExtra("city");
        double  temp= Math.round(getIntent().getDoubleExtra("temp",0))-273.15;

        int weather = getIntent().getIntExtra("weather",0);
        String date=getIntent().getStringExtra("date");
        Log.d("DetailsActivity", "onCreate: "+weather);

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        detailsActivityComponent = DaggerDetailsActivityComponent.builder()
                .detailsActivityContextModule(new DetailsActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        detailsActivityComponent.injectDetailsActivity(this);
        cityText.setText(city);
        String x= String.format(Locale.getDefault(), "%.0f°", temp);
        tempText.setText(x+"C");
        animationView.setAnimation(getWeatherAnimation(weather));
        animationView.playAnimation();
        int humidity = getIntent().getIntExtra("humidity",0);
        double windSpeed =getIntent().getDoubleExtra("windSpeed", 0);
        String description=getIntent().getStringExtra("description");

        humidityText.setText("Humidity  "+humidity);
        descriptionText.setText(description);
        windText.setText("Wind  "+windSpeed+ " km/h");






    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(mContext);
    }
    public static int getWeatherAnimation(int weatherCode) {
        if (weatherCode / 100 == 2) {
            return R.raw.storm_weather;
        } else if (weatherCode / 100 == 3) {
            return R.raw.rainy_weather;
        } else if (weatherCode / 100 == 5) {
            return R.raw.rainy_weather;
        } else if (weatherCode / 100 == 6) {
            return R.raw.snow_weather;
        } else if (weatherCode / 100 == 7) {
            return R.raw.unknown;
        } else if (weatherCode == 800) {
            return R.raw.clear_day;
        } else if (weatherCode == 801) {
            return R.raw.few_clouds;
        } else if (weatherCode == 803) {
            return R.raw.broken_clouds;
        } else if (weatherCode / 100 == 8) {
            return R.raw.cloudy_weather;
        }
        return R.raw.unknown;
    }
}
