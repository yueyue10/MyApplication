package com.encdata.zyj.myapplication.rxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * RxJava2组合操作符
 * 本节：https://www.jianshu.com/p/9d1a3fdd9ef7
 * 目录：https://www.jianshu.com/u/275291c9866e
 * Created by zhaoyuejun on 2018/4/11.
 */

public class Demo3 {

    private static String TAG = "LOG------";

    /**
     * RxJava中的zip操作符作用是
     * 将多条上游发送的事件进行结合到一起,发送到下游,并且按照顺序来进行结合,
     * 如多条上游中发送的事件数量不一致,则以最少的那条中的事件为准,下游接收到的事件数量和其相等.
     */
    public static void test1() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        });

        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("这是");
                e.onNext("这个是");
                e.onNext("这个则是");
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("个");
                e.onNext("只");
                e.onNext("条");
                e.onNext("张");
                e.onNext("本");
                e.onNext("副");
            }
        });

        Observable.zip(observable, observable1, observable2, new Function3<Integer, String, String, String>() {
            @Override
            public String apply(Integer integer, String s, String s2) throws Exception {
                return s + integer + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
//        返回结果：
//        E/LOG------: 这是1个
//        E/LOG------: 这个是2只
//        E/LOG------: 这个则是3条
    }
}
