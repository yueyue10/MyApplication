package com.encdata.zyj.myapplication.rxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.encdata.zyj.myapplication.MainActivity.Tag;

/**
 * RxJava2基础使用
 * 本节：https://www.jianshu.com/p/7c0640963bac
 * 目录：https://www.jianshu.com/u/275291c9866e
 * Created by zhaoyuejun on 2018/4/11.
 */

public class BasicUtils {
    /**
     * Observable(可观察者，即被观察者)、
     * Observer(观察者)、Subscriber
     * subscribe(订阅)、事件。
     * Observable 和 Observer 通过 subscribe() 方法实现订阅关系，从而 Observable 可以在需要的时候发出事件来通知 Observer。
     */

    /**
     * 2) 创建 Observable
     * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。
     * RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则：
     */

    public static void test1() {
        /**
         * ObservableEmitter 即Rxjava的发射器
         * 通过这个发射器,即可发送事件-----通过调用onNext,onError,onComplete方法发送不同事件.
         * Disposable 默认为false,表示发送者和接受者直接的通信阀门关闭,可以正常通信
         * 在调用dispose()方法之后,阀门开启,会阻断发送者和接收者之间的通信,从而断开连接.
         */
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //这里调用的方法会在产生事件之后会发送给接收者,接收者对应方法会收到
                e.onNext("hahaha");
                e.onNext("223");
                e.onNext("234235");
                e.onNext("454345");
                e.onError(new Exception("wulala"));
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            /**
             * 接受者,根据事件产生者产生的事件调用不同方法
             */
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(Tag, "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {
                Log.e(Tag, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(Tag, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.e(Tag, "onComplete: ");
            }
        });
//        返回结果：
//        E/LOG------: onSubscribe:
//        E/LOG------: onNext: hahaha
//        E/LOG------: onNext: 223
//        E/LOG------: onNext: 234235
//        E/LOG------: onNext: 454345
//        E/LOG------: onError:
//        java.lang.Exception: wulala
    }

    /**
     * 子线程向主线程发送消息
     */
    public static void test2() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hahaha");
                e.onNext("hahaha");
                e.onNext("hahaha");
                Log.e(Tag, "运行在什么线程" + Thread.currentThread().getName());
                e.onComplete();
            }
            //subscribeOn(),只有在第一次调用的时候生效,之后不管调用多少次,只会以第一次为准.
        }).subscribeOn(Schedulers.newThread())               //线程调度器,将发送者运行在子线程
                //observeOn(), 可以被调用多次, 每次调用都会更改线程.
                .observeOn(AndroidSchedulers.mainThread())          //接受者运行在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(Tag, "onSubscribe: ");
                        Log.e(Tag, "接收在什么线程" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e(Tag, "onNext: " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(Tag, "onComplete: ");
                    }
                });
//        RxJava线程池中的几个线程选项
//                - Schedulers.io() io操作的线程, 通常io操作,如文件读写.
//                - Schedulers.computation() 计算线程,适合高计算,数据量高的操作.
//                - Schedulers.newThread() 创建一个新线程,适合子线程操作.
//                - AndroidSchedulers.mainThread() Android的主线程,主线程
//        返回结果：
//        E/LOG------: onSubscribe:
//        E/LOG------: 接收在什么线程main
//        E/LOG------: 运行在什么线程RxNewT
//        E/LOG------: onNext: hahah
//        E/LOG------: onNext: hahah
//        E/LOG------: onNext: hahah
//        E/LOG------: onComplete:
    }

    public static void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "我是变换过后的" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(Tag, s);
            }
        });
//        返回结果：
//        E/LOG------: 我是变换过后的1
//        E/LOG------: 我是变换过后的2
//        E/LOG------: 我是变换过后的3
    }
}
