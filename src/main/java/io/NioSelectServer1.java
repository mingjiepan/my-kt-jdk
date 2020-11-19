package io;

/**
 * int[] fds = new int[]
 *
 * int fd1 = socket()
 * bind(fd1, 8899)
 * listen(fd1)
 *
 * while(true) {
 *     int fd2 = accept(fd1)
 *     fds.add(fd2)
 *     fdArr = select(fds)
 *     for (int fd : fdArr) {
 *         read(fd)
 *     }
 * }
 * 优点：避免在用户空间内调用n次的系统调用read方法，只需要调用一次系统调用select，传入待监听的文件描述符
 * 缺点，select有限制，1一次只能监听1024个，且每次都需要在用户空间将文件描述拷贝到内核空间，
 * @author panmingjie
 * @date 2020/11/19 10:24
 */
public class NioSelectServer1 {
    public static void main(String[] args) {
    }
}