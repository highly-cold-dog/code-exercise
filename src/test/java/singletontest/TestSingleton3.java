package singletontest;

import singleton.Singleton3;

/**
 * @author dlf
 * @date 2020/12/27 17:03
 */
public class TestSingleton3 {
    public static void main(String[] args) {
        Singleton3 singleton3 = Singleton3.SINGLETON_3;
        System.out.printf("result:"+singleton3);
    }
}
