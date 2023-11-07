package singletontest;

import singleton_lazy.SingletonLazy2;

import java.util.concurrent.*;

/**
 * @author dlf
 * @date 2020/12/27 17:40
 */
public class TestSingletonLazy2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<SingletonLazy2> callable = SingletonLazy2::getInstance;
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<SingletonLazy2> f1 = executorService.submit(callable);
        Future<SingletonLazy2> f2 = executorService.submit(callable);
        SingletonLazy2 s1 = f1.get();
        SingletonLazy2 s2 = f2.get();
        System.out.println(s2 == s1);
        System.out.println("s2 = " + s2);
        System.out.println("s1 = " + s1);

        executorService.shutdown();

    }
}
