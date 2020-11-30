package com.mjie.rpc.rpc03;

import com.mjie.common.domain.User;

/**
 * @author panmingjie
 * @date 2020/11/27 14:45
 */
public class Client3 {
    public static void main(String[] args) {
        Stub3 stub3 = new Stub3();
        User userById = stub3.findUserById(23);
        System.out.println(userById);
    }
}
