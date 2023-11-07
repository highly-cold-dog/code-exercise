package juc.thread.reentrantlock_demo;

/**
 * 可重入锁（也叫递归锁）
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层获取锁的时候，在进入内层方法会自动获取锁。
 * <p>
 * 也即是说，线程可以进入任何一个它已经拥有的锁所同步的代码块。
 *
 * @author dlf
 * @date 2021/3/25 23:19
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(phone::sendSMS, "t2").start();
    }
}

/**
 * 资源类
 */
class Phone {

    /**
     * 发短信
     */
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getId() + "\t" + "invoked sendSMS()");
        //验证同步方法的锁同步机制
        sendEmail();
    }

    /**
     * 发短信
     */
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getId() + "\t" + "invoked sendEmail()");
    }

}
