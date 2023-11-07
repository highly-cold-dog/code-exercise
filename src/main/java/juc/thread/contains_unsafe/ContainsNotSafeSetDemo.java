package juc.thread.contains_unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * HastSet非线程安全的集合类
 *
 * @author dlf
 * @date 2021/3/16 21:46
 */


public class ContainsNotSafeSetDemo {
    public static void main(String[] args) {
        // HashSet 底层就是HaspMap  初始值16  负载因子 0.75 add()方法的确是调用HashMap的put方法，key为当前E  但是 value为一个PRESENT的常量
        //  Set<String> stringSet = new HashSet<>();
        //解决方法 1
      //  Set<String> stringSet = Collections.synchronizedSet(new HashSet<>());
        // 解决方法2
        Set<String> stringSet = new CopyOnWriteArraySet<>(new HashSet<>());

        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                // java.util.ConcurrentModificationException
                stringSet.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(stringSet);
            }, String.valueOf(i)).start();

        }
    }

    public static void listNotSafe() {

    }
}
