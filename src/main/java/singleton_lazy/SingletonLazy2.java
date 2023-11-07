package singleton_lazy;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式线程安全的写法
 *
 * @author dlf
 * @date 2020/12/27 17:31
 */
public class SingletonLazy2 {
    private static SingletonLazy2 instance;

    private SingletonLazy2() {

    }

    public static SingletonLazy2 getInstance() {
        if (instance == null) {
            // 加上同步锁
            synchronized (SingletonLazy2.class) {
                if (instance == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    instance = new SingletonLazy2();
                }
            }

        }

        return instance;
    }
}
