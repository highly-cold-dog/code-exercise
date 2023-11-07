package singleton_lazy;

/**
 * 线程安全懒汉式写法优化版
 * 在内部类被加载和初始化时，才创建 INSTANCE 实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，他是单独去加载和初始化的
 * 因为是在内部类加载和初始化时，创建的，因此是线程安全的
 *
 * @author dlf
 * @date 2020/12/27 20:58
 */
public class SingletonLazy3 {


    // 私有化构造方法
    private SingletonLazy3() {

    }

    private static class Inner {
        private static final SingletonLazy3 INSTANCE = new SingletonLazy3();
    }

    private static SingletonLazy3 getInstance() {
        return Inner.INSTANCE;
    }
}
