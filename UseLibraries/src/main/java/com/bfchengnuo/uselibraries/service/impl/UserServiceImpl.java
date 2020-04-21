package com.bfchengnuo.uselibraries.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfchengnuo.uselibraries.entity.User;
import com.bfchengnuo.uselibraries.mapper.UserMapper;
import com.bfchengnuo.uselibraries.service.UserService;
import org.springframework.stereotype.Service;

/**
 * MP 通用 service，仅作简单示例
 * 具体参考：MPTest 测试用例
 *
 * @author 冰封承諾Andy
 * @date 2020/4/12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
