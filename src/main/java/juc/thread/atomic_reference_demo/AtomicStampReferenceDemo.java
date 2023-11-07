package juc.thread.atomic_reference_demo;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带时间戳的原子引用
 * ABA问题的解决
 *
 * @author dlf
 * @date 2021/3/4 23:25
 */
public class AtomicStampReferenceDemo {
    //普调原子引用
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(1);
    //这个留待我后面复习的时候再说吧
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("ABA问题的产生=========");
        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(1, 2));

            System.out.println(atomicReference.compareAndSet(2, 1));

            System.err.println(atomicReference.get());
        }, "t1").start();

        new Thread(() -> {

            // 暂停1s  线程，保证上面的线程全部都执行完毕
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(1, 2) + "\t" + atomicReference.get());
        }, "t2").start();

        System.out.println("ABA问题的解决============");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ABA问题的解决======");

        new Thread(() -> {
            int stamp = atomicStampedReference.getReference();
            System.out.println("t3's first stamp =" + stamp);

            // 线程再暂停1s
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(2, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("t3 最新版本号:"+atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t4's first stamp=" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"result:" + result);
        }, "t4").start();
    }
}
