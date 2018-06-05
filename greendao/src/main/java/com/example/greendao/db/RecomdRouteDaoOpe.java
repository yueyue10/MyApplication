package com.example.greendao.db;

import android.content.Context;
import android.util.Log;

import com.example.greendao.bean.RecomdRouteData;
import com.example.greendao.dao.RecomdRouteDataDao;
import com.socks.library.KLog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class RecomdRouteDaoOpe {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param recomdRouteData
     */
    public static void insertData(Context context, RecomdRouteData recomdRouteData) {
        DbManager.getDaoSession(context).getRecomdRouteDataDao().insert(recomdRouteData);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */

    public static void insertData(Context context, List<RecomdRouteData> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        try {
            DbManager.getDaoSession(context).getRecomdRouteDataDao().insertInTx(list);
        } catch (Exception e) {
            KLog.e(Log.getStackTraceString(e));
        }
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param list
     */
    public static void insertOrReplaceData(Context context, List<RecomdRouteData> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        try {
            DbManager.getDaoSession(context).getRecomdRouteDataDao().insertOrReplaceInTx(list);
        } catch (Exception e) {
            KLog.e(Log.getStackTraceString(e));
        }
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param recomdRouteData
     */
    public static void saveData(Context context, RecomdRouteData recomdRouteData) {
        DbManager.getDaoSession(context).getRecomdRouteDataDao().save(recomdRouteData);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param recomdRouteData 删除具体内容
     */
    public static void deleteData(Context context, RecomdRouteData recomdRouteData) {
        DbManager.getDaoSession(context).getRecomdRouteDataDao().delete(recomdRouteData);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getRecomdRouteDataDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getRecomdRouteDataDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param recomdRouteData
     */
    public static void updateData(Context context, RecomdRouteData recomdRouteData) {
        DbManager.getDaoSession(context).getRecomdRouteDataDao().update(recomdRouteData);
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<RecomdRouteData> queryAll(Context context) {
        QueryBuilder<RecomdRouteData> builder = DbManager.getDaoSession(context).getRecomdRouteDataDao().queryBuilder();
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
    public static List<RecomdRouteData> queryPaging(int pageSize, int pageNum, Context context) {
        RecomdRouteDataDao recomdRouteDataDao = DbManager.getDaoSession(context).getRecomdRouteDataDao();
        List<RecomdRouteData> listMsg = recomdRouteDataDao.queryBuilder().offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }
} 