package com.mjie.common.service;

import com.mjie.common.domain.User;

/**
 * @author panmingjie
 * @date 2020/11/27 09:38
 */
public interface UserService {
    User findUserById(Integer id);
}
