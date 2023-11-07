package juc.thread.contains_unsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合非线程安全
 *
 * @author dlf
 * @date 2021/3/7 23:15
 */
public class ContainsNotSafeDemo {
    public static void main(String[] args) {

        //  stringList.forEach(System.out::println);
        //初始值为10
        new ArrayList<Integer>();
        //模拟非线程安全
        List<String> list = new ArrayList<>();
        //解决方法 1
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 解决办法2
        //  List<String> list = new CopyOnWriteArrayList<>() ;
        /**
         * Vector 相对于 AL 添加了    synchronized 锁，加锁能保证数据一致性，但是并发性急剧下降
         */
        //  List<String> list = new Vector<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // java.util.ConcurrentModificationException
                System.out.println(list);
            }, String.valueOf(i)).start();

        }


    }


}

