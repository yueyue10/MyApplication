package com.example.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.greendao.adapter.MessageListAdapter;
import com.example.greendao.bean.JsonStrData;
import com.example.greendao.bean.MessCtData;
import com.example.greendao.bean.MessageBean;
import com.example.greendao.bean.RecomdRouteData;
import com.example.greendao.db.JsonStrDaoOpe;
import com.example.greendao.db.MessCtDaoOpe;
import com.example.greendao.db.RecomdRouteDaoOpe;
import com.example.greendao.utils.ConverterUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的消息
 * Created by zhaoyuejun on 2017/11/15.
 */
//我的消息
public class MyMessageAc extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.listview)
    ListView listview;

    private List<MessageBean> datas = new ArrayList<MessageBean>();
    private MessageListAdapter mAdapter;
    //    private String[] titles = {"系统通知", "行程提醒", "精彩推荐", "咨询回复", "游前小提示", "订单消息"};
    private String[] titles = {"行程提醒", "精彩推荐", "景点播报", "导引消息"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_mymessage);
        setTitle("我的消息");
        setImageBack();
        //初始化列表
        initdata();
        initListView();
    }

    /**
     * 初始化数据
     */
    private void initdata() {
        for (int i = 0; i < titles.length; i++) {
            MessageBean messageBean = null;
            switch (i) {
                case 0:
                    messageBean = new MessageBean("system", titles[i]);
                    break;
                case 1:
                    messageBean = new MessageBean("travel", titles[i]);
                    break;
                case 2:
                    messageBean = new MessageBean("wonderful", titles[i]);
                    break;
                case 3:
                    messageBean = new MessageBean("ask", titles[i]);
                    break;
                case 4:
                    messageBean = new MessageBean("travel_tips", titles[i]);
                    break;
                case 5:
                    messageBean = new MessageBean("order", titles[i]);
                    break;
            }
            datas.add(messageBean);
        }
    }

    /**
     * init Listview
     */
    private void initListView() {
        mAdapter = new MessageListAdapter(MyMessageAc.this, datas);
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(this);
    }

    @OnClick({R.id.add_recomd_routetv, R.id.add_recomd_spottv, R.id.clear_recomd_routetv, R.id.clear_recomd_spottv, R.id.clear_jsontv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_recomd_routetv://新增偏航数据
                List<RecomdRouteData> recomdRouteData = getLocalData("RecomdRoute.json", new TypeToken<List<RecomdRouteData>>() {
                }.getType());
                String radomStr = ConverterUtils.getRandomString(10);
                for (int i = 0; i < recomdRouteData.size(); i++) {
                    recomdRouteData.get(i).setUpdateTime(radomStr);
                }
                RecomdRouteDaoOpe.insertOrReplaceData(MyMessageAc.this, recomdRouteData);
                JsonStrDaoOpe.insertData(mContext, new JsonStrData(null, getLocalStr("RecomdRoute.json")));
                break;
            case R.id.add_recomd_spottv://新增推荐数据
                List<MessCtData> messCtData = getLocalData("WuWCData.json", new TypeToken<List<MessCtData>>() {
                }.getType());
                MessCtDaoOpe.insertData(MyMessageAc.this, messCtData);
                JsonStrDaoOpe.insertData(mContext, new JsonStrData(null, getLocalStr("WuWCData.json")));
                break;
            case R.id.clear_recomd_routetv://清除偏航数据
                RecomdRouteDaoOpe.deleteAllData(mContext);
                break;
            case R.id.clear_recomd_spottv://清除推荐数据
                MessCtDaoOpe.deleteAllData(mContext);
                break;
            case R.id.clear_jsontv://清除JSON数据
                JsonStrDaoOpe.deleteAllData(mContext);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (titles[position]) {
            case "行程提醒":
                startActivity(new Intent(this, GuideRemindAc.class)
                        .putExtra("title", titles[0]));
                break;
            case "精彩推荐":
                startActivity(new Intent(this, GuideRecomdAc.class)
                        .putExtra("title", titles[1])
                        .putExtra("type", "recomd_other"));
                break;
            case "景点播报":
                startActivity(new Intent(this, GuideRecomdAc.class)
                        .putExtra("title", titles[2])
                        .putExtra("type", "spot_media"));
                break;
            case "导引消息":
                startActivity(new Intent(this, MyJsonListAc.class)
                        .putExtra("title", titles[3]));
                break;
        }
    }

}