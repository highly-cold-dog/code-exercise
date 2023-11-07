package juc.thread.reentrantlock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dlf
 * @date 2021/3/25 23:35
 */
public class ReenterLockDemo2 {
    public static void main(String[] args) {
        ResourceClass resourceClass = new ResourceClass();
        //  验证 synchronized 是一个典型的可重入锁
        new Thread(resourceClass::sendSMS, "t1").start();
        new Thread(resourceClass::sendSMS, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(resourceClass::get, "t3").start();
    }
}

class ResourceClass implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t" + "sendSMS()");
        sendEMAIL();
    }

    public synchronized void sendEMAIL() {
        System.out.println(Thread.currentThread().getName() + "\t" + "SendEMAIL()");
    }

    //   默认非公平锁
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        //加几把锁都一样
        //  lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "invoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            //   lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}