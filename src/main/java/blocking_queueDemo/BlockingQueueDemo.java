package blocking_queueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayLockingQueue:  是一个基于数据结构的有界阻塞队列，此队列FIFO(先进先出)原则对元素进行排序
 * LinkedBlockingQueue : 是一个基于链表的阻塞队列，此队列按照FIFO(先进先出)排序，吞吐量通常高于ArrayBlockingQueue
 * SynchronousQueue : 一个存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞
 * 状态，吞吐量通常要高
 * <p>
 * 1  队列
 * 2  阻塞队列
 * 2.1  阻塞队列好的一面
 * 2.2  不得不阻塞，你如何管理
 *
 * @author dlf
 * @date 2021/4/11 17:01
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));

        System.out.println(blockingQueue.add("b"));

        System.out.println(blockingQueue.add("c"));
        //  add() 可能会发生的异常:

        // check()  简单返回队首元素
        System.err.println(blockingQueue.element());

        // System.out.println(blockingQueue.add("d"));
        // 当阻塞队列满时，再往队列里add插入元素会抛出 IllegalStateException : Queue full
        //          符   合先进先出
        System.out.println(blockingQueue.remove());

        System.out  .println(blockingQueue.remove());

        System.out.println(blockingQueue.remove());

        // 当队列为空时也会发生异常
        //   System.out.println(blockingQueue.remove());

    }
}
