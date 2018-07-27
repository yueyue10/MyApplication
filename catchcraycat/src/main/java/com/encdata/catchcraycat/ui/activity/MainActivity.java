package com.encdata.catchcraycat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.encdata.catchcraycat.ui.bean.Dot;
import com.encdata.catchcraycat.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int COL = 10;
    private int ROW = 10;

    Dot matrix[][] = new Dot[COL][ROW];
    TextView catchcrazycat_tv, customview_tv, dateview_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catchcrazycat_tv = findViewById(R.id.catchcrazycat_tv);
        customview_tv = findViewById(R.id.customview_tv);
        dateview_tv = findViewById(R.id.dateview_tv);
        setAllClick();
    }

    private void setAllClick() {
        catchcrazycat_tv.setOnClickListener(this);
        customview_tv.setOnClickListener(this);
        dateview_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.catchcrazycat_tv:
                startActivity(new Intent(this, CrazyCatActivity.class));
                break;
            case R.id.customview_tv:
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
            case R.id.dateview_tv:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
        }
    }
}
