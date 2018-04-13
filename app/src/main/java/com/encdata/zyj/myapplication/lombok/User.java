package com.encdata.zyj.myapplication.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 生成toString()方法，默认情况下，它会按顺序（以逗号分隔）打印你的类名称以及每个字段。
 * 可以这样设置不包含哪些字段@ToString(exclude = "id") / @ToString(exclude = {"id","name"})
 * 如果继承的有父类的话，可以设置callSuper 让其调用父类的toString()方法，例如：@ToString(callSuper = true)
 */
@NoArgsConstructor()
@RequiredArgsConstructor()
public class User extends Obj {
    /**
     * 你可以用@Getter / @Setter注释任何字段（当然也可以注释到类上的），让lombok自动生成默认的getter / setter方法。
     * 默认生成的方法是public的，如果要修改方法修饰符可以设置AccessLevel的值，
     * 例如：@Getter(access = AccessLevel.PROTECTED)
     */
    @NonNull
    @Getter(AccessLevel.PUBLIC)
    @Setter
    private Integer id;
    @NonNull
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String phone;
}
