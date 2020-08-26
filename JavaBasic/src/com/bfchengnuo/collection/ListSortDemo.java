package com.bfchengnuo.collection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 冰封承諾Andy
 * @date 2020/7/28
 */
public class ListSortDemo {
    /**
     * 一个 List 进行排序，计算出排名，分数相同的排名一样，下一个就跳过
     * @param args
     */
    public static void main(String[] args) {
        List<User> userList = List.of(new User("mps1", 2),
                new User("kkk", 6),
                new User("ww", 3),
                new User("aa", 2),
                new User("qq", 43),
                new User("tt", 3),
                new User("ff", 2),
                new User("gg", 22)
                );

        // 定义计数器，或者使用 reduce 两次 stream 操作
        final int[] rankIndexAndPreScore = {1, 1, -1};
        userList.stream()
                .sorted(Comparator.comparing(User::getScore))
                .map(user -> {
                    if (rankIndexAndPreScore[1] == 1) {
                        rankIndexAndPreScore[1]++;
                        rankIndexAndPreScore[2] = user.getScore();
                        user.setRanking(rankIndexAndPreScore[0]);
                        return user;
                    }
                    if (user.getScore() == rankIndexAndPreScore[2]) {
                        user.setRanking(rankIndexAndPreScore[0]);
                        rankIndexAndPreScore[1]++;
                        return user;
                    }
                    rankIndexAndPreScore[0] = rankIndexAndPreScore[1]++;
                    rankIndexAndPreScore[2] = user.getScore();
                    user.setRanking(rankIndexAndPreScore[0]);
                    return user;
                })
                // .collect(Collectors.toList())
                .forEach(System.out::println);
    }


    static class User {
        private String name;
        private Integer score;
        private Integer ranking;

        public User(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Integer getRanking() {
            return ranking;
        }

        public void setRanking(Integer ranking) {
            this.ranking = ranking;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    ", ranking=" + ranking +
                    '}';
        }
    }
}
