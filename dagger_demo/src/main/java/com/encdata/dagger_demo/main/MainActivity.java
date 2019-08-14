package com.encdata.dagger_demo.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.encdata.dagger_demo.BuildConfig;
import com.encdata.dagger_demo.R;
import com.encdata.dagger_demo.di.http.DataManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.text);
        text.setText(dataManager.getApiService().getName());
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, BuildConfig.USERNAME, Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", BuildConfig.USERNAME);
            }
        });
    }

}
