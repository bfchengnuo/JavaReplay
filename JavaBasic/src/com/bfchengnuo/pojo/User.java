package com.bfchengnuo.pojo;

/**
 * @author 冰封承諾Andy
 * @date 2020/6/7
 */
public class User {
    private String name;
    private Integer age;
    private String desc;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                '}';
    }
}
