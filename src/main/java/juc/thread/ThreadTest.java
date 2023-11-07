package juc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 处理List返回List
 *
 * @author dlf
 * @date 2021/2/26 17:20
 */
public class ThreadTest {

    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        List<Map<String,Integer>> lists2 = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            lists.add(String.valueOf(i));
        }
        System.out.print("原始的数据:"+lists);

        System.err.print("处理好的数据"+changeData(lists));

    }

    public static List<String> changeData(List<String> needChange) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);

        List<String> result = new ArrayList<>();
        TestCallable testCallable = new TestCallable(needChange);
        if (!executorService.isShutdown()){
            completionService.submit(testCallable);
        }
        try{
            result = completionService.take().get();
        }catch (Exception e){
            e.printStackTrace();

        }

        executorService.shutdown();
        return   result;

    }

    static class TestCallable implements Callable<List<String>> {

        private final List<String> stringList;

        //赋值
        public TestCallable(List<String> stringList) {
            this.stringList = stringList;
        }


        @Override
        public List<String> call() throws Exception {
            //实际上生效的是这个被重写的call方法
            stringList.forEach(this::changeValue);
            return stringList;
        }

        public void changeValue(String str) {

            //模拟业务处理耗时
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.print(str);
        }
    }


}

