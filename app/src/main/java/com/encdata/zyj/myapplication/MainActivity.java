package com.encdata.zyj.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.encdata.zyj.myapplication.lambda.LambdaDemo1;
import com.encdata.zyj.myapplication.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

/**
 * RxJava使用场景小结---未完，待续
 * https://blog.csdn.net/lzyzsd/article/details/50120801
 * Created by zhaoyuejun on 2018/4/11.
 */
public class MainActivity extends AppCompatActivity {

    public static final String Tag = "LOG------";
    ImageView display_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        process();
//        RxDeviceUtils.
        Intent i_getvalue = getIntent();
        String action = i_getvalue.getAction();
        TextView textView=findViewById(R.id.btn_throttle);
        //html文件在h5APP1项目里面的example的app-link.html文件中进行测试>button.html界面中测试
        if(Intent.ACTION_VIEW.equals(action)){
            Uri uri = i_getvalue.getData();
            if(uri != null){
                String name = uri.getQueryParameter("name");
                String age= uri.getQueryParameter("age");
                textView.setText(name);
            }
        }
    }

    private void process() {
        display_iv = findViewById(R.id.display_iv);
        RxView.clicks(findViewById(R.id.btn_throttle))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(aVoid -> {
                            doSomething();
                        }
                );
        RxView.clicks(display_iv)
                .throttleFirst(1, TimeUnit.SECONDS)//两秒钟之内只取一个点击事件，防抖操作
//                .buffer(1, TimeUnit.SECONDS)//定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。
//                .debounce(2, TimeUnit.SECONDS)//Observable发射的速率过快的
                .subscribe(aVoid -> display_iv.setVisibility(View.GONE));
    }

    public void doSomething() {
        LambdaDemo1.test1(display_iv);
    }

}
