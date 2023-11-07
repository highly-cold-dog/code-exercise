package blocking_queueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 由数组组成的有界阻塞队列
 *
 * @author dlf
 * @date 2021/4/14 1:06
 */
public class BlockingQueueDemo3 {
    public static void main(String[] args) throws Exception {

        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //没有返回值
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("==============");
        // 当阻塞队列满时，生产者线程会往队列里put元素，队列一直阻塞生产线程直到put数据or响应中断退出
        //blockingQueue.put("d");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        // take() 消费者方法也会阻塞
        blockingQueue.take();


    }

}
