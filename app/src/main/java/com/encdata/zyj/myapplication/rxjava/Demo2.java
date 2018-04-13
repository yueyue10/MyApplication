package com.encdata.zyj.myapplication.rxjava;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava2过滤操作符
 * 本节：https://www.jianshu.com/p/2200486b5d9a
 * 目录：https://www.jianshu.com/u/275291c9866e
 * Created by zhaoyuejun on 2018/4/11.
 */

public class Demo2 {

    public static String Tag = "LOG------";

    /**
     * filter使用
     * 我们朝下游发送了10000个数据,而我只需要其中可以被7整除的数据,利用filter,将其他的数据过滤出去,留下需要的数据.
     */
    public static void test1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 10000; i++) {
                    e.onNext(i);
                }
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 7 == 0;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(Tag, integer + "");
            }
        });
//        返回结果：
//        E/LOG------: 0
//        E/LOG------: 7
//        E/LOG------: 14
//        E/LOG------: 21
//            ...
//        E/LOG------: 9975
//        E/LOG------: 9982
//        E/LOG------: 9989
//        E/LOG------: 9996
    }

    /**
     * sample使用
     * 使用sample之后,每隔1秒对上游数据采样一次,发送到下游,其他事件则被过滤.
     */
    public static void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }).sample(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(Tag, integer + "");
                    }
                });
//        返回结果：
//        E/LOG------: 265254
//        E/LOG------: 541705
//        E/LOG------: 808733
//        E/LOG------: 1071514
//        E/LOG------: 1343250
//        E/LOG------: 1601545
//        E/LOG------: 1865979
//              ...
    }

    /**
     * take和takeLast方法可以将上游事件中的前N项或者最后N项发送到下游,其他事件则进行过滤
     */
    public static void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 10; i++) {
                    e.onNext(i);
                }
            }
        })
                .take(3)
//                .takeLast(3)//没有作用
//                .range(7, 10)//有作用
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(Tag, integer + "");
                    }
                });
//        返回结果：
//        E/LOG------: 0
//        E/LOG------: 1
//        E/LOG------: 2
    }

    /**
     * distinct方法,可以将重复对象去除重复对象,
     * repeat(),产生重复事件
     */
    public static void test4() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 50; i++) {
                    e.onNext(i);
                }
            }
        }).take(3)
                //生成重复事件
                .repeat(3)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(Tag, integer + "");
                    }
                });
//        返回结果：
//        E/LOG------: 0
//        E/LOG------: 1
//        E/LOG------: 2
    }
}
