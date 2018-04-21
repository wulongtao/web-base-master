package com.xxh.web.service.impl;

import com.xxh.web.dao.UserDO;
import com.xxh.web.mapper.UserMapper;
import com.xxh.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小小黑
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO findUserByUserId(Integer id) {

        return userMapper.findUserByUserId(id);
    }
}
