package com.example.greendao.db;

import android.content.Context;
import android.util.Log;

import com.example.greendao.bean.Student;
import com.example.greendao.dao.StudentDao;
import com.socks.library.KLog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class StudentDaoOpe {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param stu
     */
    public static void insertData(Context context, Student stu) {
        DbManager.getDaoSession(context).getStudentDao().insert(stu);
    }

    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<Student> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        try {
            DbManager.getDaoSession(context).getStudentDao().insertInTx(list);
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
    public static void insertOrReplaceData(Context context, List<Student> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        try {
            DbManager.getDaoSession(context).getStudentDao().insertOrReplaceInTx(list);
        } catch (Exception e) {
            Log.d("Log-----", Log.getStackTraceString(e));
        }
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param student
     */
    public static void saveData(Context context, Student student) {
        DbManager.getDaoSession(context).getStudentDao().save(student);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param student 删除具体内容
     */
    public static void deleteData(Context context, Student student) {
        DbManager.getDaoSession(context).getStudentDao().delete(student);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getStudentDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getStudentDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param student
     */
    public static void updateData(Context context, Student student) {
        DbManager.getDaoSession(context).getStudentDao().update(student);
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<Student> queryAll(Context context) {
        QueryBuilder<Student> builder = DbManager.getDaoSession(context).getStudentDao().queryBuilder();
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
    public static List<Student> queryPaging(int pageSize, int pageNum, Context context) {
        StudentDao studentDao = DbManager.getDaoSession(context).getStudentDao();
        List<Student> listMsg = studentDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }
} 