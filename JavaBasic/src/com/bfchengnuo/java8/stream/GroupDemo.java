package com.bfchengnuo.java8.stream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 使用集合的 stream 来进行分组聚合的小例子
 *
 * @author Created by 冰封承諾Andy on 2019/6/24.
 */
public class GroupDemo {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("name1", 10, '女'));
        userList.add(new User("name2", 11, '男'));
        userList.add(new User("name3", 12, '女'));
        userList.add(new User("name4", 11, '女'));
        userList.add(new User("name5", 12, '女'));


        // 聚合分组，首先按照性别分，然后再按照年龄分
        Map<Character, Map<Integer, List<User>>> collectMap = userList.stream().collect(
                Collectors.groupingBy(
                        User::getSex,
                        // 二级分组
                        Collectors.groupingBy(User::getAge)
                ));

        List<ResultVo> resultVoList = new ArrayList<>(collectMap.size());
        collectMap.forEach((sex, mapData) -> {
            ResultVo vo = new ResultVo();
            vo.setSex(sex);

            Map<Integer, List<String>> map = new HashMap<>(mapData.size());
            mapData.forEach((age, users) -> {
                List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
                // 做数字聚合的话，例如所有人的年龄和
                int ageSum = users.stream().mapToInt(User::getAge).sum();
                map.put(age, names);
            });

            vo.setData(map);
            resultVoList.add(vo);
        });

        System.out.println(resultVoList);
    }

    /**
     * 聚合之前的实体结构
     */
    private static class User {
        private String name;
        private Integer age;
        private Character sex;

        public User() {
        }

        public User(String name, Integer age, Character sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
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

        public Character getSex() {
            return sex;
        }

        public void setSex(Character sex) {
            this.sex = sex;
        }
    }

    /**
     * 聚合之后的实体结构
     * 方便起见，不制造更多的小对象，使用 Map 来代替
     */
    private static class ResultVo implements Serializable {
        private Character sex;
        private Map<Integer, List<String>> data;

        public Character getSex() {
            return sex;
        }

        public void setSex(Character sex) {
            this.sex = sex;
        }

        public Map<Integer, List<String>> getData() {
            return data;
        }

        public void setData(Map<Integer, List<String>> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "ResultVo{" +
                    "sex=" + sex +
                    ", data=" + data +
                    '}';
        }
    }
}
