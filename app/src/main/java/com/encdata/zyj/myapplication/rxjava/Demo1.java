package com.encdata.zyj.myapplication.rxjava;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava2之变换操作符
 * 本节：https://www.jianshu.com/p/42df17cc25ac
 * 目录：https://www.jianshu.com/u/275291c9866e
 * Created by zhaoyuejun on 2018/4/11.
 */

public class Demo1 {

    private static String TAG = "LOG------";

    public static void test1() {
        //模拟一篇文章
        String article = "fkjdsalijfofldaJFOIEjfldanlJR2OnfldajilwafkndaIUPO32,LFKjlijuJFLMA";
        char[] chars = article.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            Log.e(TAG, chars[i] + "");
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                sb.append((chars[i] + "").toUpperCase());
            } else {
                sb.append(chars[i]);
            }
        }
        Log.e(TAG, sb.toString());
//        返回结果：
//        E/LOG------: f
//        E/LOG------: k
//        E/LOG------: j
//        下面的省略
//        E/LOG------: FKJDSALIJFOFLDAJFOIEJFLDANLJR2ONFLDAJILWAFKNDAIUPO32,LFKJLIJUJFLMA
    }

    /**
     * map使用
     * 瞬间逼格就上去了,而且还做了线程调度等操作,是不是心头顿时感觉一串666飘过,这仅仅是最基础的转化操作符,接下来我们在看一个FlatMap:
     */
    public static void test2() {
        //模拟一篇文章
        String article = "fkjdsalijfofldaJFOIEjfldanlJR2OnfldajilwafkndaIUPO32,LFKjlijuJFLMA";
        final char[] chars = article.toCharArray();
        final StringBuilder stringBuilder = new StringBuilder();
        Observable.create(new ObservableOnSubscribe<Character>() {
            @Override
            public void subscribe(ObservableEmitter<Character> e) throws Exception {
                for (int i = 0; i < chars.length; i++) {
                    e.onNext(chars[i]);
                }
            }
            //delay  延时5秒发送
        }).delay(1, TimeUnit.SECONDS)
                //事件类型转换
                .map(new Function<Character, String>() {
                    @Override
                    public String apply(Character s) throws Exception {
                        if (s >= 'a' && s <= 'z') {
                            return s.toString().toUpperCase();
                        } else {
                            return s.toString();
                        }
                    }
                })
                //线程调度
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, s);
                        stringBuilder.append(s);
                    }
                });
        Log.e(TAG, stringBuilder.toString());
//        返回结果：
//        E/LOG------: f
//        E/LOG------: k
//        E/LOG------: j
//        下面的省略，并不能得到stringBuilder的值
    }

    /**
     * flatMap的使用>>>ConcatMap是有序的，flatMap是无需的
     */
    public static void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 5; i++) {
                    list.add("我是变换过的" + integer);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
//        返回的结果：
//        E/LOG------: 我是变换过的1
//        E/LOG------: 我是变换过的1
//        E/LOG------: 我是变换过的1
//        E/LOG------: 我是变换过的1
//        E/LOG------: 我是变换过的1
//        E/LOG------: 我是变换过的2
//        E/LOG------: 我是变换过的2
//        E/LOG------: 我是变换过的2
//        E/LOG------: 我是变换过的2
//        E/LOG------: 我是变换过的2
//        E/LOG------: 我是变换过的3
//        E/LOG------: 我是变换过的3
//        E/LOG------: 我是变换过的3
//        E/LOG------: 我是变换过的3
//        E/LOG------: 我是变换过的3
    }

}
