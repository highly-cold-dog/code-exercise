package singletontest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dlf
 * @date 2023/5/13 16:08
 */
public class RuableTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 准备数据
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        // 分段处理
        int len = 10;
        List<Future<List<Integer>>> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i += len) {
            final int start = i;
            final int end = Math.min(start + len, list.size());
            Callable<List<Integer>> task = new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    List<Integer> subList = new ArrayList<>();
                    for (int j = start; j < end; j++) {
                        subList.add(list.get(j) * 2);
                    }
                    return subList;
                }
            };
            Future<List<Integer>> future = executor.submit(task);
            results.add(future);
        }

        // 汇总结果
        List<Integer> resultList = new ArrayList<>();
        for (Future<List<Integer>> future : results) {
            resultList.addAll(future.get());
        }

        System.out.println(resultList);
    }
}
