package com.encdata.zyj.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    String Tag = "LOG------";

    //    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.encdata.zyj.myapplication", appContext.getPackageName());
    }

    @Test
    public void test1() {
        System.out.println(Tag + "开始");
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
                e.onError(new Exception("wulala"));
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            /**
             * 接受者,根据事件产生者产生的事件调用不同方法
             */
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(Tag + "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {
                System.out.println(Tag + "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(Tag + "onError: " + e);
            }

            @Override
            public void onComplete() {
                System.out.println(Tag + "onComplete: ");
            }
        });
    }
}
