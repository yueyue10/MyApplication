package com.encdata.zyj.myapplication.lombok;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Accessors 主要用于控制生成的getter和setter
 * fluent boolean值，默认为false。此字段主要为控制生成的getter和setter方法前面是否带get/set
 * chain boolean值，默认false。如果设置为true，setter返回的是此对象，方便链式调用方法
 * prefix 设置前缀 例如：@Accessors(prefix = "abc") private String abcAge 当生成get/set方法时，会把此前缀去掉
 */
@Data
@Accessors(fluent = true, chain = true)
public class User4 {
    private Integer sbid;
    private String name;
    private String sbphone;
}
