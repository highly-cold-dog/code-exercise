package singletontest;

import singleton_lazy.SingletonLazy1;

import java.util.concurrent.*;

/**
 * @author dlf
 * @date 2020/12/27 17:15
 */
public class TestSingletonLazy1 {
    public static void main(String[] args) throws Exception {
        // 单线程的情况下
        /*SingletonLazy1 singletonLazy1 = SingletonLazy1.getInstance();
        SingletonLazy1 singletonLazy2 = SingletonLazy1.getInstance();
        if (singletonLazy1 == singletonLazy2) {
            System.out.printf("是同一个对象!");
        } else {
            System.out.printf("并没有获得同一个对象");
        }
        System.out.println("singletonLazy1:" + singletonLazy1);
        System.out.println("singletonLazy2:" + singletonLazy2);*/

        //多下称情况下可能存在线程安全问题
        Callable<SingletonLazy1> c = SingletonLazy1::getInstance;
        //创建线程池的方式
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 提交任务
        Future<SingletonLazy1> f1 = executorService.submit(c);
        // 提交任务
        Future<SingletonLazy1> f2 = executorService.submit(c);
        SingletonLazy1 s1 = f1.get();
        SingletonLazy1 s2 = f2.get();
        System.out.println(s1 == s2);
        System.out.printf("s1:" + s1);
        System.out.printf("s2:" + s2);
        //关闭线程池
        executorService.shutdown();
    }
}
