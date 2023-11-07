package classinit;

import java.util.Arrays;
import java.util.List;

/**
 * 子类的初始化<clint></>
 * (1) j =method()
 * (2)子类的静态代码块
 * 先初始化父类
 *
 * @author dlf
 * @date 2020/12/27 21:13
 */
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.println("(6)");
    }

    Son() {
        System.out.println("(7)");
    }

    public int test() {
        System.out.println("(9)");
        return 1;
    }

    public static int method() {
        System.out.println("(10)");
        return 1;
    }

    /**
     * 考点 类初始化过程
     * 实例初始化过程
     * 方法的重新
     * main方法所在的类需要先加载和初始化
     *
     * @param args result
     */
    public static void main(String[] args) {
/*
    一个类要创建实例需要先加载并初始化该类
        main方法所在的类需要先加载和初始化
        一个子类需要初始化需要先初始化父类
        一个类初始化就是执行<clint>()方法
        <clint>()方法由静态类变量显式赋值代码和静态代码块组成
        类变量显示赋值代码和静态代码块从上到下顺序执行
        <clint>()方法只执行一次
 */
        // 以下创建对象的代码注释掉以后仅仅会发生类的初始化，不会发生实例的初始化
        Son s1 = new Son();
        System.out.println();
        /*
        实例初始化就是执行<init>()方法
        <init>()方法可能重载有多个，有几个构造器就有几个<init>方法
        <init>()方法由非静态实例变量显示赋值代码和非静态代码块，对应构造器代码组成
        子类的实例化方法():
        1)super()
            非静态变量
        2)i = test()

        3)子类的非静态代码块
        4)子类的无参构造(最后)
         */
        Son s2 = new Son();

        String[] donArr = {"DON"};
        //待修改 县级退回，市级退回
        String[] apmArr = {"LRJT","GCRJT"};
        //待审核 县级待审，市级待审
        String[] apmApr = {"LAPR","CAPR"};
        List<String[]> strings = Arrays.asList(donArr, apmArr,apmApr);

        for (String[] a: strings
             ) {
            System.err.println(a.length);
        }
    }
}
