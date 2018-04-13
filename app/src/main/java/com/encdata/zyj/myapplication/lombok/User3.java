package com.encdata.zyj.myapplication.lombok;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * 生成一个全参数的构造方法
 */

@AllArgsConstructor
public class User3 extends Obj {
    private Integer id;
    private String name;
    private final String phone;
}
