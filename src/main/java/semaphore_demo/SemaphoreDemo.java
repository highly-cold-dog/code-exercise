package semaphore_demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author dlf
 * @date 2021/4/11 0:03
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 模拟3个停车位 默认非公平锁 非公平锁相对来说效率更高
        Semaphore semaphore = new Semaphore(3);

        // 模拟 6部汽车
        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    //  抢占到车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    System.err.println("现在的车位:" + semaphore.availablePermits());
                    // 线程暂停一会
                    TimeUnit.SECONDS.sleep(3);

                    System.out.println(Thread.currentThread().getName() + "\t 停车3s后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //  释放车位
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
