package com.mjie.common.service.impl;

import com.mjie.common.domain.User;
import com.mjie.common.service.UserService;

/**
 * @author panmingjie
 * @date 2020/11/27 09:38
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(Integer id) {
        return new User(id, "hello");
    }
}
