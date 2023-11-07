package blocking_queueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dlf
 * @date 2021/4/18 23:03
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println("生产线程启动!");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        //消费线程启动
        new Thread(() -> {
            System.out.println("消费者线程启动!");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

        System.out.println();

        System.out.println();

        System.out.println("5s 时间到！  活动停止!");

        myResource.stop();

    }
}

class MyResource {
    //开关  默认开启，进行生产和消费 保证线程之间的可加性
    private volatile static boolean FLAG = true;
    //原子引用 默认为0
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    private MyResource th;

    /**
     * 构造方法注入
     *
     * @param blockingQueue blockingQueue
     */
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    /**
     * 生产者
     *
     * @throws Exception Exception
     */
    public void myProd() throws Exception {
        String data;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";

            // 往队列里传数据  2s  取一个
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功!");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败！");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 被叫停! 表时FLAG = false，生产结束");

    }

    /**
     * 消费者
     *
     * @throws Exception Exception
     */
    public void myConsumer() throws Exception {
        String result;
        boolean resultVal;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没有取到蛋糕，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功 !");
        }
    }

    /**
     * 停止
     *
     * @throws Exception Exception
     */
    public void stop() throws Exception {
        FLAG = false;
    }

}
