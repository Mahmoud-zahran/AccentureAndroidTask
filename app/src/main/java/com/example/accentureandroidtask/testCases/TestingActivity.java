package com.example.accentureandroidtask.testCases;

import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accentureandroidtask.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class TestingActivity extends AppCompatActivity {
    private EditText txtResult;
    private EditText txtSummery;

    private int lineIndex = 0;
    private int counterForHundler = 0;
    private int showSpeed = 20;
    private int countImageComeFromNet = 0;
    private int countImageComeFromCashe = 0;
    private int countJsonComeFromNet = 0;
    private int countJsonComeFromCashe = 0;
    private int countCanceled = 0;
    private int countFailure = 0;

    private Handler mHandler = new Handler();
    private String[] urlsImageArray =
            {
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128&s=622a88097cf6661f84cd8942d851d9a2",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702"
            };
    private String[] urlsJsonArray =
            {
                    "http://ip.jsontest.com/",
                    "http://pastebin.com/raw/wgkJgazE",
                    "http://pastebin.com/raw/wgkJgazE"
            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        txtResult = (EditText) findViewById(R.id.txtResult);
        txtSummery = (EditText) findViewById(R.id.txtSummery);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
            txtResult.setText("");
            lineIndex = 0;
            counterForHundler = 0;
            countImageComeFromCashe = 0;
            countImageComeFromNet = 0;
            countJsonComeFromNet = 0;
            countJsonComeFromCashe = 0;
            countCanceled = 0;
            countFailure = 0;
            runTestCode();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runTestCode();
                }
            }, 1000);

            return true;
        } else if (id == R.id.action_clearCashe) {

            Toast.makeText(this, "Cache Cleared", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void runTestCode() {


    }

    private void logInEditText(final String msg) {
        counterForHundler++;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (lineIndex == 0)
                    txtResult.append(lineIndex++ + ": " + msg);
                else
                    txtResult.append("\n" + lineIndex++ + ": " + msg);
                txtSummery.setText("Image From Net: " + countImageComeFromNet);
                txtSummery.append("\nImage From Cashe: " + countImageComeFromCashe);
                txtSummery.append("\nJson From Net: " + countJsonComeFromNet);
                txtSummery.append("\nJson From Cache: " + countJsonComeFromCashe);
                txtSummery.append("\nCanceled: " + countCanceled);
                txtSummery.append("\nFailure: " + countFailure);
            }
        }, counterForHundler * showSpeed);

    }



    public class IPClass {
        @SerializedName("ip")
        @Expose
        private String ip;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

}
