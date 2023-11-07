package blocking_queueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 由数组组成的有界阻塞队列
 *
 * @author dlf
 * @date 2021/4/14 1:14
 */
public class BlockingQueueDemo4 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
    }
}
