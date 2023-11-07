package juc.thread.contains_unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlf
 * @date 2021/3/16 23:40
 */
public class ContainsNotSafeMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        //解决方法 1
        //   Map<String, String> map = new ConcurrentHashMap<>();
        //解决方法2
        // Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                // java.util.ConcurrentModificationException
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.err.println(map);
            }, String.valueOf(i)).start();

        }
    }
}
