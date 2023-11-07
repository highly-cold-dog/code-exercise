package cas_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 自旋锁的好处:
 *      循环比较 获取直到成功为止，没有类似  wait  的阻塞
 *   通过CAS操作完成自旋锁，A线程先进来调用  myLock方法自己持有锁 5 秒钟,
 *   B 随后进来发现当前线程持有锁，不是 null ，所以只能自旋等待，直到 A释放后B抢到
 * @author dlf
 * @date 2021/3/30 22:07
 */
public class SpinLockDemo {
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in=====");

        // A 线程第一次进入不参与循环
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoke myUnLock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            //线程暂停一会
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(() ->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"t2").start();


    }
}
