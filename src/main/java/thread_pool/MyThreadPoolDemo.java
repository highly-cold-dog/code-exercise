package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * <p> 备注： 实现Runnable  不会抛异常 实现Callable 会抛异常
 * 第四种获得使用Java多线程的方式，线程池。
 * <p>
 * 线程池的底层都是这个类
 * new ThreadPoolExecutor()
 *
 * @author dlf
 * @date 2021/4/19 0:47
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //创建线程池
        /*
         1 固定线程数的线程池 执行长期任务，性能好很多 一池五个线程
         底层：
         new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>()
                                      构造方法重载：
                                       public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);

              public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
    }

         */
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

          /*
         2 一池一线程 一个任务一个任务执行的场景  Executors.newSingleThreadExecutor();
         底层
         new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>())
         */
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        /*
         3  一池多线程 执行很多短期异步的小程序或者负载较轻的服务 Executors.newCachedThreadPool();
         底层
          new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>()
         */
        ExecutorService executorService3 = Executors.newCachedThreadPool();


        //模拟 10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            //  threadPool.submit();
            //  模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 0; i < 10; i++) {
                //无返回值的线程，搭配线程池使用技术
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");

                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        // 单个线程的使用
        try {
            for (int i = 0; i < 20; i++) {
                executorService2.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务！");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService2.shutdown();
        }

        //可缓存的  一池多线程 但是这个线程的最大数是  Integer的最大值
        try {

            for (int i = 0; i < 100; i++) {
                executorService3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 在忙呢！");

                });
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService3.shutdown();
        }

    }
}
