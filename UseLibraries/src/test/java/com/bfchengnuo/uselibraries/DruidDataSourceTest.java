package com.bfchengnuo.uselibraries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试配置的数据库连接池
 *
 * @author Created by 冰封承諾Andy on 2019/9/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DruidDataSourceTest {
    @Autowired
    @Qualifier("druidDataSource")
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }
}
