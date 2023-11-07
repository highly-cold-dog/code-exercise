package thread_pool;

import java.util.concurrent.*;

/**
 * 手写线程池的写法
 *
 * @author dlf
 * @date 2021/5/4 23:41
 */
public class MyThreadPoolDemo2 {

    public static void main(String[] args) {
        //获取CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 2线程核心数 5线程最大数》包含2 1s等待时间 阻塞队列最好是有个初值，否则默认Integer的最大值
        ExecutorService executorService = new ThreadPoolExecutor(
                // 核心数
                2,
                // 最大数
                5,
                //等待时间
                1L,
                TimeUnit.SECONDS,
                //  阻塞队列候客区
                new LinkedBlockingQueue<>(3),
                //
                Executors.defaultThreadFactory(),
                // 1拒绝策略 》 发生异常的最大数就是  最大数5+阻塞队列3  1. new ThreadPoolExecutor.AbortPolicy()直接抛异常
                //2 new ThreadPoolExecutor.CallerRunsPolicy() 调用者运行，一种调节机制，
                // 该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
                //3 DiscardOldestPolicy() 抛弃队列中等待最久的任务，然后把当前任务加入队列尝试再次提交当前任务
                //4 DiscardPolicy() 直接丢弃任务，不予任务处理也不抛出异常。如果允许任务丢失，这是最好的一种方案。
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 100; i++) {
                executorService.execute(() -> System.out.println(Thread.currentThread().getName() + "\t办理业务!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
