package com.encdata.zyj.myapplication.lambda;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by zhaoyuejun on 2018/4/12.
 */
@Data
public class Persion {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private int age;

    public Persion(String yixing, String zhao, int i) {
        this.firstName = yixing;
        this.lastName = zhao;
        this.age = i;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
