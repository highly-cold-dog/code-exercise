package callable_demo;

import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 多线程中，第三种获得多线程方式
 *
 * @author dlf
 * @date 2021/4/18 23:52
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //无返回值线程的使用
        new Thread(new MyThread(), "AAA").start();

        //有返回值的使用
        FutureTask futureTask = new FutureTask(new MyThread2(2));
        Thread thread = new Thread(futureTask, "BBB");
        thread.start();
        //  多个线程去争抢同一个FutureTask 只被执行一次
        new Thread(futureTask,"CCC").start();

        //要求获得Callable线程的计算结果，如果没有计算完成就要求去强求，会导致阻塞，直到计算完成
        //System.out.println("######result"+futureTask.get());
        //  关于优化  带有返回值的线程还没有算完的情况下的处理 类似自旋锁的思想
        while (!futureTask.isDone()){

        }

        System.out.println();

        System.out.println("######result"+futureTask.get());


        FutureTask futureTaskMockName = new FutureTask(new MockName("张三"));
        Thread threadMockName = new Thread(futureTaskMockName,"futureTaskMockName");
        threadMockName.start();


        FutureTask futureTaskMockAddress = new FutureTask(new mockAddressCallable("张家屯"));
        Thread threadMockAddress = new Thread(futureTaskMockAddress,"futureTaskMockAddress");
        threadMockAddress.start();


    /*   while (!futureTaskMockName.isDone()){

       }*/

        System.err.println("futureTaskMockName:"+futureTaskMockName.get());



     /*  while (!futureTaskMockAddress.isDone()){
       }*/

        System.err.println("futureTaskMockAddress:"+futureTaskMockAddress.get());



        System.err.println("所有线程执行完毕!");


        System.out.println("嘿嘿嘿");
    }
}

/**
 * 无返回值线程的使用
 */
class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

/**
 * 有返回值的线程使用
 */
class MyThread2 implements Callable<Integer> {
    private Integer num;

    public MyThread2(Integer i) {
        this.num = i;
    }

    @Override
    public Integer call() throws Exception {

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t come in =====");
        return num;
    }
}


class MockName implements Callable<List<JSONObject>> {

    private final String name;

    public MockName(String name) {
        this.name = name;
    }


    @Override
    public List<JSONObject> call() throws Exception {
        System.err.println(Thread.currentThread().getName()+"\t come in =====");

        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("age", ++i);
            result.add(json);
        }
        return result;
    }


}


class mockAddressCallable implements Callable<List<JSONObject>> {

    private final String address;

    public mockAddressCallable(String address) {
        this.address = address;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public List<JSONObject> call() throws Exception {
        return mockAddress(address);
    }

    /**
     * 模拟生成地址信息数据
     *
     * @return
     */
    List<JSONObject> mockAddress(String address) {
        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            JSONObject json = new JSONObject();
            json.fluentPut("name", "张山");
            json.fluentPut("address", address + (++i) + "号胡同");
            result.add(json);
        }
        return result;
    }
}
