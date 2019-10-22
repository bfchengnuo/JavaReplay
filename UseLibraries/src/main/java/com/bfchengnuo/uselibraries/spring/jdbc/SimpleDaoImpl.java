package com.bfchengnuo.uselibraries.spring.jdbc;

import com.bfchengnuo.uselibraries.common.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Spring JdbcTemplate 的简单示例
 *
 * @author Created by 冰封承諾Andy on 2019/9/18.
 */
@Service
public class SimpleDaoImpl implements SimpleDao<User, Integer> {
    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Override
    public boolean add(User user) {
        String sql = "insert into user values(?,?,?)";
        int result = secondaryJdbcTemplate.update(sql, user.getName(), user.getAge(), user.getDesc());
        return result > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "delete from user where id=?";
        int result = secondaryJdbcTemplate.update(sql, id);
        return result > 0;
    }

    @Override
    public boolean updateById(User user, Integer id) {
        String sql = "update user set name=?, age=? where id=?";
        int result = secondaryJdbcTemplate.update(sql, user.getName(), user.getAge(), user.getId());
        return result > 0;
    }

    public Integer queryCount(String name) {
        String sql = "select count(*) from user";
        if (StringUtils.isNoneBlank(name)) {
            sql +=  " where name like concat('%', '" + name + "' ,'%')";
        }
        return secondaryJdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public User queryById(Integer id) {
        String sql = "select * from user where id=?";
        return secondaryJdbcTemplate.queryForObject(sql, (rs, index) -> {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setAge(rs.getInt("age"));
            return u;
        }, id);
    }

    public List<User> querySome(String name, int start, int max) {
        String sql = "select * from user";
        if (StringUtils.isNoneBlank(name)) {
            sql +=  " where name like concat('%', '" + name + "' ,'%')";
        }
        sql += " limit ?,?";

        List<User> list = secondaryJdbcTemplate.query(sql, (rs, index) -> {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setAge(rs.getInt("age"));
            return u;
        }, start, max);

        return list;
    }

    public boolean check(String name, String desc) {
        String sql = "select id from user where name=? and desc=?";
        // queryForObject 返回为空会抛异常
        List<Map<String,Object>> list = secondaryJdbcTemplate.queryForList(sql, name, desc);
        return list.size() != 0;
    }
}
