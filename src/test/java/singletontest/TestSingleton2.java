package singletontest;

import singleton.Singleton2;

/**
 * @author dlf
 * @date 2020/12/27 16:51
 */
public class TestSingleton2 {
    public static void main(String[] args) {
     /*   Singleton2 singleton2 = Singleton2.INSTANCE;
        System.out.printf(singleton2 + "");*/

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                System.err.println(Singleton2.INSTANCE);;
            },String.valueOf(i)).start();
        }
    }
}
