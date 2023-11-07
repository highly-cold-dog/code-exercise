package juc.thread.countdouwnlatch_demo;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 线程倒计数工具类的使用
 *
 * @author dlf
 * @date 2021/4/8 22:19
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                countDownLatch.countDown();
            }, Objects.requireNonNull(CountryEnum.foreach_countryEnum(i)).getReMessage()).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.err.println(CountryEnum.ONE);
        System.err.println(CountryEnum.ONE.getReCode());
        System.err.println(CountryEnum.ONE.getReMessage());

        System.out.println(Thread.currentThread().getName() + "\t 秦国统一六国#####");
    }

}
