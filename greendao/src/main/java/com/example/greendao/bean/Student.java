package com.example.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {

    @Id(autoincrement = true)
    private Long id;  
    private String name;  
    private int age;  
    private String num;
    private String grade;
    @Generated(hash = 266394775)
    public Student(Long id, String name, int age, String num, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.num = num;
        this.grade = grade;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getGrade() {
        return this.grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", num='" + num + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}