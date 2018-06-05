package com.example.greendao;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.greendao.utils.ConverterUtils;
import com.example.greendao.utils.GsonUitls;
import com.example.greendao.utils.JsonParserUtils;
import com.google.gson.reflect.TypeToken;
import com.socks.library.KLog;

import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zhaoyuejun on 2018/6/5.
 */

public class BaseActivity extends Activity {

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public RelativeLayout setImageBack() {
        RelativeLayout ib_back = (RelativeLayout) findViewById(R.id.ib_back_layout);
        ib_back.setVisibility(View.VISIBLE);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return ib_back;
    }

    /**
     * 设置页面的标题 <br>
     * 在 layout/base_title中 <include layout="@layout/base_title"/>
     */
    public TextView setTitle(String title) {
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_title.setVisibility(View.VISIBLE);
        return tv_title;
    }

    /**
     * 从assets文件夹获取json数据转成List数据
     */
    public <T> List<T> getLocalData(String assets_res, Type type) {
        try {
            InputStream in = getAssets().open(assets_res);
            String content = ConverterUtils.convertStreamToString(in);
            JSONObject jo = new JSONObject(content);
            String value = JsonParserUtils.parserJsonObjToStr(jo, "value");
            List<T> json_List = GsonUitls.parseJSON(value, type);
            return json_List;
        } catch (Exception e) {
            KLog.e(Log.getStackTraceString(e));
            return null;
        }
    }

    /**
     * 从assets文件夹获取字符串
     */
    public String getLocalStr(String assets_res) {
        try {
            InputStream in = getAssets().open(assets_res);
            String content = ConverterUtils.convertStreamToString(in);
            return content;
        } catch (Exception e) {
            KLog.e(Log.getStackTraceString(e));
            return null;
        }
    }
}
