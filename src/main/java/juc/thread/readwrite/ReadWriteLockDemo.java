package juc.thread.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 多个线程同时读取一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该有其他线程可以对该资源类进行读或者写
 * 总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 *
 * @author dlf
 * @date 2021/3/30 23:04
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        //写入
        for (int i = 0; i < 5; i++) {
            final int flag = i;
            new Thread(() -> {
                cache.put(flag + "", flag + "");
            }, String.valueOf(i)).start();
        }

        //  读取
        for (int i = 0; i < 5; i++) {
            final int flag = i;
            new Thread(() -> {
                cache.get(flag + "");
            }, String.valueOf(i)).start();

        }
    }
}

class Cache {
    // 保证可见性
    private volatile Map<String, Object> map = new HashMap<>();

    // 读写锁
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {


        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成:" + key);

      /*  readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成:" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }*/

    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在读:" + key);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成:" + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }


}
