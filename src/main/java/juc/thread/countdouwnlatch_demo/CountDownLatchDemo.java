package juc.thread.countdouwnlatch_demo;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * @author dlf
 * @date 2021/4/8 22:14
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        //线程计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "上晚自习，离开教室===");
                //线程数量自减
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        // 线程阻塞
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t 班长锁门");





    }


}





