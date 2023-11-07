package singleton;

/**
 * @author dlf
 * 饿汉式
 * 直接创建实例，不管你是否需要这个对象
 * (1) 构造器私有化
 * (2) 自行创建，并且使用静态变量保存
 * (3) 向外提供这个实例
 * (4) 强调这是一个单例，我们可以用final
 */
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    //对外暴露
    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
