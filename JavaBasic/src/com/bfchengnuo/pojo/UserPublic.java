package com.bfchengnuo.pojo;

/**
 * 供 {@link com.bfchengnuo.juc.volat.ArrayAndObjTest} 测试
 * @author 冰封承諾Andy
 * @date 2020/6/7
 */
public class UserPublic {
    public String name;
    public volatile int age;
    public String desc;

    public UserPublic() {
    }

    public UserPublic(String name) {
        this.name = name;
    }

    public UserPublic(String name, int age) {
        this.name = name;
        this.age = age;
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
