package com.encdata.dagger_demo.main;

import android.os.Bundle;
import android.widget.TextView;

import com.encdata.dagger_demo.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Student student;
    @Inject
    Persion persion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView a = findViewById(R.id.text);
        a.setText(student.getName());
    }
}
