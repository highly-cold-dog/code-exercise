package dead_lock;

import java.util.concurrent.TimeUnit;

//持有锁的线程
class HoldLockThread implements Runnable {
    private final String lockA;
    private final String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockB = lockB;
        this.lockA = lockA;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有:" + lockA + "\t尝试获取:" + lockB);

            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有:" + lockB + "\t尝试获取:" + lockA);
            }

        }
    }
}

/**
 * 死锁
 *
 * @author dlf
 * @date 2021/5/5 16:08
 * 死锁是指两个或两个以上的进程执行过程中，因争夺资源而造成的一种互相等待的现象
 * 若无外力干涉那他们都将无法推进下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();

        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

        //  jps

        /**
         * linux ps -ef|grep java ls-l
         * windows下的java运行程序  也有类似ps的查看进程命令，但是目前我们需要查看只是java
         *          jps = java ps jps-l
         */
    }
}
