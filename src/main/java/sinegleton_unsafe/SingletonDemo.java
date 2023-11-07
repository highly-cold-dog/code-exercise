package sinegleton_unsafe;

/**
 * @author dlf
 * @date 2021/2/28 21:29
 */
public class SingletonDemo {

    // volatile 禁止指令重排
    private static volatile SingletonDemo instance = null;

    // 构造器私有化
    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法被调用");
    }

    /**
     * 加上 synchronized 可以解决并发问题，但是不推荐
     * 因为太重了，并发性能会下降
     *
     * @return result
     */
  /*  public static synchronized SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }*/

    /*
    DCL (Double Check Lock 双端检索机制)
    双端检索机制也并不能保证完全的线程安全，因为存在指令重排序
    原因在于某一个线程执行到第一次检测，读取到的instance不为null时，
    instance的引用对象 可能没有完成初始化
     */
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
     /*   System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.instance);*/


        //并发多线程以后，情况发生了变化
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(101);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
