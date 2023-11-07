package volatileDemo;

/**
 * 单例模式共计6中
 * 1懒汉式  2饿汉式 3懒汉式线程安全 4内部类
 * @author dlf
 * @date 2021/2/12 13:09
 */
public class SingletonDemo {

    private static SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.printf(Thread.currentThread().getName()+"我是构造方法SingletonDemo()");
    }

    public static SingletonDemo getInstance(){
        if (instance == null){
            instance =  new SingletonDemo();
        }
        return instance;
    }
    public static void main(String[] args) {
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //开启多个线程进行访问
        for (int i = 0; i < 10; i++) {

           new Thread(() ->{

           },String.valueOf(i)).start();

        }
    }
}
