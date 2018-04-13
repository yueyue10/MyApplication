package com.encdata.zyj.myapplication.lombok;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

/**
 * @Delegate 这个注解也是相当的牛逼，看下面的截图，它会该类生成一些列的方法，这些方法都来自与List接口
 */
public class User5 {
    private Integer sbid;
    private String name;
    private String sbphone;
    @Delegate
    private List<String> addreses = new ArrayList<>();
}
