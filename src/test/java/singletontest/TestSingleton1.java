package singletontest;

import singleton.Singleton1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author dlf
 * @date 2020/12/27 16:49
 */
public class TestSingleton1 {
    public static void main(String[] args) {

    }

    public static void testSy(){
        Singleton1 singleton1 = Singleton1.getInstance();

        Singleton1 singleton2 = Singleton1.getInstance();
        System.out.println(singleton1 == singleton2);
    }

    public static void testThreadPool(){

        //创建带有返回值得线程
        Callable<Singleton1> callable = Singleton1 ::getInstance;
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //提交
        Future<Singleton1> future1 = executorService.submit(callable);

    }
}
