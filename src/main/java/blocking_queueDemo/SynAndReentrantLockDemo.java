package blocking_queueDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Synchronized和ReentrantLock之间的区别
 * <p>
 * 题目 ： 多线程之间顺序调用，实现 A > B >  C 三个线程启动，要求如下：
 * AA 打印5次 BB打印１０次，CC打印１５次
 * 紧接着
 * AA 打印5次 BB打印１０次，CC打印１５次
 * 来１０轮
 *
 * @author dlf
 * @date 2021/4/18 15:46
 */
public class SynAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}

/**
 * 资源类
 */
class ShareResource {

    //  A  1  B  2  C  3
    private int number = 1;
    //准备加锁
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void print15() {
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                condition3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //  3  通知 1
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {

        lock.lock();
        try {
            // 1.判断
            while (number != 2) {
                condition2.await();
            }
            // 2.干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //  3 通知
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print5() {
        lock.lock();
        try {
            // 1判断
            while (number != 1) {

                condition1.await();
            }
            //2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3 通知
            //  修改标志位
            number = 2;
            // 通知 》  通知线程2
            condition2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
