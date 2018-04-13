package com.encdata.zyj.myapplication.lombok;

import com.encdata.zyj.myapplication.Klog;

import java.util.Arrays;

/**
 * Lombok简介
 * https://www.jianshu.com/p/365ea41b3573
 */

public class Demo1 {


    public static void test() {
        User user = new User();//NoArgsConstructor
        User user0 = new User(2, "你");//RequiredArgsConstructor+NonNull
        User1 user1 = new User1(1, "Wang", "121213123");//RequiredArgsConstructor+NonNull+final
        User2 user2 = User2.of(1, "Wang", "123213123");//RequiredArgsConstructor(staticName = "of")
        User3 user3 = new User3(1, "Zhang", "23123123");//AllArgsConstructor
//        User4 user4 = new User4().setName("lao").setSbid(1).setSbphone("123");
        User4 user4 = new User4().name("lao").sbid(1).sbphone("123");
        Klog.d(user0.getName());
        Klog.d(user1.toString());
//        Klog.d(user.getCode());
        Klog.d(user4.toString());
        User5 user5 = new User5();
        user5.add("");
//        输出：
//        D/LOG------: 你
//        D/LOG------: User1(super=com.encdata.zyj.myapplication.lombok.User1@8e5cca2, id=1, phone=121213123)
//        D/LOG------: User4(sbid=1, name=lao, sbphone=123)
    }

    public static void test1() {
        BuilderExample builderExample = new BuilderExample.BuilderExampleBuilder().name("22").age(1).occupation("程序员").build();
        BuilderExample builderExample1 = new BuilderExample.BuilderExampleBuilder().name("22").age(1).occupations(Arrays.asList("程序员", "工程师", "叼炸天")).build();
        Klog.d(builderExample.toString());
        Klog.d(builderExample1.toString());
//        输出：
//        D/LOG------: BuilderExample(name=22, age=1, occupations=[程序员])
//        D/LOG------: BuilderExample(name=22, age=1, occupations=[程序员, 工程师, 叼炸天])
    }

    public static void test2() {
        SynchronizedExample synchronizedExample = new SynchronizedExample();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedExample.hello();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedExample.hello();
            }
        });
        Thread threadC = new Thread(() -> synchronizedExample.hello());
        threadA.start();
        threadB.start();
        threadC.start();
//        输出：
//        Thread-1279 synchronized loop 0
//        Thread-1279 synchronized loop 1
//        Thread-1279 synchronized loop 2
//        Thread-1279 synchronized loop 3
//        Thread-1279 synchronized loop 4
//        Thread-1280 synchronized loop 0
//        Thread-1280 synchronized loop 1
//        Thread-1280 synchronized loop 2
//        Thread-1280 synchronized loop 3
//        Thread-1280 synchronized loop 4
    }

}
