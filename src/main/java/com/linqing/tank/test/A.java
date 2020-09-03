package com.linqing.tank.test;

/**
 * 测试类加载和初始化的顺序
 */
public class A {

    private static int b = 20;

    private static int a = b;

    public A(){
        System.out.println("无参构造执行");
    }

    private static A aa = new A();



    static {
        System.out.println(b);
        b = 30;
        System.out.println("静态代码块执行");
        aa.c = 34;
    }

    private int c = 0;

    public static int methodA(){
        System.out.println("静态方法执行");
        return A.a;
    }

    public static A getInstance(){
        return aa;
    }



    public static void main(String[] args) {


        System.out.println("a的c" + A.aa.c);
        System.out.println(A.methodA());
    }

    /**
     * 一个类初始化执行过程
     * 类加载的时候静态成员变量默认初始化和静态代码块执行按照顺序执行 --  执行构造方法 -- 静态方法调用的时候才执行
     */






}
