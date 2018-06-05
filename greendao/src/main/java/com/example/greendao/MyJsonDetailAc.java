package com.example.greendao;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

/**
 * json数据展示界面
 * Created by zhaoyuejun on 2018/6/2.
 */
public class MyJsonDetailAc extends BaseActivity {

    @BindView(R.id.content_tv)
    TextView content_tv;
    String title = "正在建设中...";
    String content = "正在建设中...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_json_detail);
        if (getIntent().getStringExtra("title") != null)
            title = getIntent().getStringExtra("title");
        if (getIntent().getStringExtra("content") != null)
            content = getIntent().getStringExtra("content");
        setTitle(title);
        setImageBack();
        content_tv.setText(format(content.replace("JsonStrData", "")));
    }

    /**
     * 得到格式化json数据  退格用\t 换行用\r
     */
    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}