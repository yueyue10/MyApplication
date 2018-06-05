package com.example.greendao.db;

import android.content.Context;
import android.util.Log;

import com.example.greendao.bean.MessCtData;
import com.example.greendao.dao.MessCtDataDao;
import com.socks.library.KLog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MessCtDaoOpe {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param messCtData
     */
    public static void insertData(Context context, MessCtData messCtData) {
        try {
            DbManager.getDaoSession(context).getMessCtDataDao().insert(messCtData);
        } catch (Exception e) {
            KLog.e(Log.getStackTraceString(e));
        }
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<MessCtData> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        try {
            DbManager.getDaoSession(context).getMessCtDataDao().insertInTx(list);
        } catch (Exception e) {
            KLog.e(Log.getStackTraceString(e));
        }
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param messCtData
     */
    public static void saveData(Context context, MessCtData messCtData) {
        DbManager.getDaoSession(context).getMessCtDataDao().save(messCtData);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param messCtData 删除具体内容
     */
    public static void deleteData(Context context, MessCtData messCtData) {
        DbManager.getDaoSession(context).getMessCtDataDao().delete(messCtData);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getMessCtDataDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getMessCtDataDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param messCtData
     */
    public static void updateData(Context context, MessCtData messCtData) {
        DbManager.getDaoSession(context).getMessCtDataDao().update(messCtData);
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<MessCtData> queryAll(Context context) {
        QueryBuilder<MessCtData> builder = DbManager.getDaoSession(context).getMessCtDataDao().queryBuilder();
        return builder.build().list();
    }


    /**
     * 分页加载
     *
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public static List<MessCtData> queryPaging(int pageSize, int pageNum, Context context) {
        MessCtDataDao messCtDataDao = DbManager.getDaoSession(context).getMessCtDataDao();
        List<MessCtData> listMsg = messCtDataDao.queryBuilder().offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }
} 