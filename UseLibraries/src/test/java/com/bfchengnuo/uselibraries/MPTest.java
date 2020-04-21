package com.bfchengnuo.uselibraries;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bfchengnuo.uselibraries.entity.User;
import com.bfchengnuo.uselibraries.mapper.UserMapper;
import com.bfchengnuo.uselibraries.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * MybatisPlus test
 * @author 冰封承諾Andy
 * @date 2020/4/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MPTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        IPage<User> page = userService.page(new Page<>(1, 3), Wrappers.<User>lambdaQuery().ne(User::getAge, 24));
        page.getRecords().forEach(System.out::println);

        userService.lambdaQuery()
                .ne(User::getAge, 24)
                .page(new Page<>(1, 3))
                .getRecords().forEach(System.out::println);
    }
}
