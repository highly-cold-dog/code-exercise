package juc.thread.atomic_reference_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author dlf
 * @date 2021/3/4 23:35
 */
public class ABADemo {
    //原子引用
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    //带有版本号的原子引用
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(201, 1);

    public static void main(String[] args) {

   /*     System.out.println("ABA问题的产生=============");
        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(100, 101));

            System.out.println(atomicReference.compareAndSet(101, 127));
            System.out.println(atomicReference.compareAndSet(127, 200));

        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            System.out.println(atomicReference.get());
            System.out.println(atomicReference.compareAndSet(200, 100) + "\t"
                    + atomicReference.get());
        }, "t2").start();*/

        System.out.println("ABA问题的解决=========");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"原来的stamp:"+stamp);
            boolean result = atomicStampedReference.compareAndSet(201, 200, 1, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"result:" + result);
        }, "t3").start();

        try {
            Thread.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"stamp:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4").start();
    }
}
