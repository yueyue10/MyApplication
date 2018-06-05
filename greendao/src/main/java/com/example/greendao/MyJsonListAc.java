package com.example.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.greendao.adapter.RvCommonListAdapter;
import com.example.greendao.bean.JsonStrData;
import com.example.greendao.db.JsonStrDaoOpe;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Json数据列表界面
 * Created by zhaoyuejun on 2018/6/1.
 */

public class MyJsonListAc extends BaseActivity {

    @BindView(R.id.content_tv)
    TextView content_tv;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RvCommonListAdapter rvCommonListAdapter;
    String title = "正在建设中...";
    String content = "正在建设中...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_json_list);
        if (getIntent().getStringExtra("title") != null)
            title = getIntent().getStringExtra("title");
        if (getIntent().getStringExtra("content") != null)
            content = getIntent().getStringExtra("content");
        setTitle(title);
        setImageBack();
//        content_tv.setText(content);
        List<JsonStrData> list = JsonStrDaoOpe.queryAll(this);
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            KLog.d(list.get(i).toString());
            stringList.add(list.get(i).toString());
        }
//        content_tv.setText(list.toString());
        rvCommonListAdapter = new RvCommonListAdapter(MyJsonListAc.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvCommonListAdapter);
        rvCommonListAdapter.refreshData(stringList);
        rvCommonListAdapter.setOnItemClickListener(new RvCommonListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(MyJsonListAc.this, MyJsonDetailAc.class)
                        .putExtra("title", "JSON详情")
                        .putExtra("content", stringList.get(position)));
            }
        });
    }

}