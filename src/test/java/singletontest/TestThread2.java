package singletontest;
import cn.hutool.core.lang.UUID;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dlf
 * @date 2023/5/13 16:14
 */
public class TestThread2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //最终返回数据
        List<String> A=new ArrayList<>();
        //带有返回值得线程挂载泛型数据
        List<Future<String>> futureList=new ArrayList<>();
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //线程执行数据处理
        for (int i = 0; i < 1000; i++) {
            Future<String> submit = executor.submit(() -> UUID.randomUUID().toString());
            futureList.add(submit);
        }
        //线程判断数据是否完成
        for (Future<String> future : futureList) {
            while (!(future.isDone())) {
                System.out.println("future : " + future.isDone());
            }
            //如果完成则添加返回值
            A.add(future.get());
        }
        //处理结束打印结果
        System.out.println(A.toString());
        System.out.println(A.size());
    }
}
