package com.mjie.rpc.rpc02;

import com.mjie.common.domain.User;

/**
 * 对客户端来说，stub封装了底层网络通讯的细节
 * @author panmingjie
 * @date 2020/11/27 11:23
 */
public class Client2 {
    public static void main(String[] args) {
        Stub1 stub1 = new Stub1();
        User user = stub1.findUserById(2);
        System.out.println(user);
    }
}
