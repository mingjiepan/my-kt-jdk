package com.mjie.asm;


/**
 *
 *
 *  0 bipush 8    把8压入栈
 *  2 istore_1    把栈顶元素存储到局部变量序号为1的变量里面
 *  3 iload_1     把局部变量序号为1的变量压入栈中
 *  4 iinc 1 by 1  对局部变量序号为1的变量加1
 *  7 istore_1    把栈顶元素存储到局部变量序号为1的变量
 *  8 getstatic #2 <java/lang/System.out>
 * 11 iload_1
 * 12 invokevirtual #3 <java/io/PrintStream.println>
 * 15 return
 */
public class Test1 {
    public static void main(String[] args) {
        int a = 8;
        a = a++;
        System.out.println(a);
    }
}
