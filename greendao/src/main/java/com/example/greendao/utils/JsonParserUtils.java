package com.example.greendao.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParserUtils {

    public static String parserJsonObjToStr(JSONObject jo, String key) throws JSONException {
        if (!jo.isNull(key)) {
            return jo.getString(key);
        } else {
            Log.e("Log---", "JsonParserException---not find key for '" + key + "'");
            return "";
        }
    }

    public static int parserJsonObjToInt(JSONObject jo, String key) throws JSONException {
        if (!jo.isNull(key)) {
            return jo.getInt(key);
        } else {
            Log.e("Log---", "JsonParserException---not find key for '" + key + "'");
            return -1;
        }
    }

    public static double parserJsonObjToDouble(JSONObject jo, String key) throws JSONException {
        if (!jo.isNull(key)) {
            return jo.getDouble(key);
        } else {
            Log.e("Log---", "JsonParserException---not find key for '" + key + "'");
            return -1;
        }
    }

    public static JSONArray parserJsonArray(JSONObject jo, String key) throws JSONException {
        if (!jo.isNull(key)) {
            return jo.getJSONArray(key);
        } else {
            Log.e("Log---", "JsonParserException---not find key for '" + key + "'");
            return new JSONArray();
        }
    }
}
