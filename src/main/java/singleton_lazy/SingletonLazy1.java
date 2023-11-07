package singleton_lazy;

import java.util.concurrent.TimeUnit;

/**
 * 第一步构造器私有化
 * 第二步类中使用静态变量保存唯一实例
 * 这种方法适合单线程非线程安全的情况
 *
 * @author dlf
 * @date 2020/12/27 17:11
 */
public class SingletonLazy1 {
    private static SingletonLazy1 INSTANCE;

    // step1 构造器私有化
    private SingletonLazy1() {

    }

    /**
     * 这种方法目前是存在线程城安全问题的
     *
     * @return result
     */
    public static SingletonLazy1 getInstance() {
        if (INSTANCE == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE = new SingletonLazy1();

        }
        return INSTANCE;
    }
}
