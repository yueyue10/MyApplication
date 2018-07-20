package com.encdata.catchcraycat;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private int COL = 10;
    private int ROW = 10;

    Dot matrix[][] = new Dot[COL][ROW];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new Playground(this));
    }
}
