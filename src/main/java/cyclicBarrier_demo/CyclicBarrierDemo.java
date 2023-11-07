package cyclicBarrier_demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 有一个内存屏障点  做加法
 * @author dlf
 * @date 2021/4/10 23:47
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // Runnable 是函数式接口 推荐使用匿名内部类
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{
            System.out.println("**** 召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            final  int  tempInt = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第:"+tempInt+"龙珠");
               try{
                   //先到的被阻塞
                   cyclicBarrier.await();
               }catch (InterruptedException | BrokenBarrierException e){
                   e.printStackTrace();
               }
            },String.valueOf(i)) .start();

        }

    }
}
