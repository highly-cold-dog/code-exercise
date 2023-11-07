package cas_test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dlf
 * @date 2021/2/28 23:09
 */
public class CASDemo {
    // CAS是什么？  比较交换 Compare And Set
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println("刷新结果:" + atomicInteger.compareAndSet(5, 2019) + ",刷新后:" + atomicInteger.get());
        System.out.println("刷新结果:" + atomicInteger.compareAndSet(5, 2014) + ",刷新后:" + atomicInteger.get());
    }
}
