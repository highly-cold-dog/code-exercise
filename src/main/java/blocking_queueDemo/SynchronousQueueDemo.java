package blocking_queueDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 不存储元素的阻塞队列，也即单个元素的队列
 *
 *  生产一个消费一个  ，你不拿走我不生产
 *  SynchronousQueue 没有容量
 *      与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的阻塞队列
 *      每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然
 *
 *
 * @author dlf
 * @date 2021/4/14 1:18
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        // 初始值非公平锁
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        //  不存储的特性

        // A线程
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        // B线程  > A线程不被消费 B线程中无法插入数据
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"\t"+ blockingQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"\t"+ blockingQueue.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"\t"+ blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
