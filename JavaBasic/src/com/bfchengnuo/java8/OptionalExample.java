package com.bfchengnuo.java8;

import java.util.Optional;

/**
 * J8 新增的空值处理 Optional， 借鉴了不少 Guava 设计
 *
 * 由于 Optional 类设计时就没特别考虑将其作为类的字段使用，所以它也并未实现 Serializable 接口
 *
 * 常用方法：
 *    get() 是这些方法中最简单但又最不安全的方法。如果变量存在，它直接返回封装的变量值，否则就抛出一个 NoSuchElementException 异常。
 *    orElse(T other)  不存在时，提供一个默认值；
 *    orElseGet(Supplier<? extends T> other) 是 orElse 方法的延迟调用版， Supplier 方法只有在 Optional 对象不含值时才执行调用（适用于创建值比较费劲的情况）。
 *    orElseThrow(Supplier<? extends X> exceptionSupplier) 和 get 方法非常类似，可以自定义抛出的异常；
 *    ifPresent(Consumer<? super T>) 让你能在变量值存在时执行一个作为参数传入的方法，否则就不进行任何操作。
 *
 * 与 Stream 对象一样， Optional 也提供了类似的基础类型 —— OptionalInt 、 OptionalLong 以及 OptionalDouble
 * 但是不推荐使用，因为基本类型的 Optional 会丧失一些好用的方法
 *
 * Optional 配合 filter 来过滤的例子参考：
 * see https://github.com/bfchengnuo/MyRecord/blob/master/%E7%AC%94%E8%AE%B0/Java/Java8%2B/OptionalExample.java
 *
 * @author Created by 冰封承諾Andy on 2019/6/27.
 */
public class OptionalExample {
    public static void main(String[] args) {
        // 基本使用
        defineAndUse();
    }

    /**
     * 基本使用
     */
    private static void defineAndUse() {
        // 声明一个空的 Optional
        Optional<String> s1 = Optional.empty();

        // 依据一个非空值创建 Optional，如果为空立即抛出 NPE 异常
        String str = "Hello";
        Optional<String> s2 = Optional.of(str);
        System.out.println(s2.get());

        // 可接受 null 的 Optional
        str = null;
        Optional<String> s3 = Optional.ofNullable(str);

        // 使用 map 从 Optional 对象中提取和转换值，避免异常
        Optional<User> optInsurance = Optional.of(new User("skye", 15));
        // 如果 Optional 为空，就什么也不做
        Optional<String> name = optInsurance.map(User::getName);
        // get 配合 isPresent，不过和一般的 null 检查太像了， 推荐使用更简洁的 ifPresent
        name.ifPresent(System.out::println);

        /*
         * 链式调用时嵌套式 optional 结构问题（使用 flatMap 解决）
         *
         * 实体类中如果使用 Optional 定义属性，那么 getter 方法获取到的就是 Optional 类型
         */
        Person p = new Person();
        p.setName("skye");
        p.setPetName(Optional.of("dd"));
        Optional<Person> person = Optional.of(p);
        person.flatMap(Person::getPetName)
                // getName 返回的是 String 类型
                .map(String::toString)
                .orElse("Unknown");
        System.out.println(person.get());

        // 类似的三元运算符；
        // person 不存在就不会执行 Lambda（car.map），
        // car 不存在就不会执行 Lambda（test方法）；
        // 只要有一个不存在就会返回一个空 Optional
        // person.flatMap(p -> car.map(c -> test(p, c)));
    }

    private static class User{
        private String name;
        private Integer age;

        public User() {
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
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
    }

    private static class Person{
        private String name;
        // 由于不支持序列化，IDEA 会有警告，可以使用 Google 的 Guava
        private Optional<String> petName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getPetName() {
            return petName;
        }

        public void setPetName(Optional<String> petName) {
            this.petName = petName;
        }

        // 一直序列化的替代方案
        public Optional<String> getNameAsOptional() {
            return Optional.ofNullable(name);
        }
    }
}
