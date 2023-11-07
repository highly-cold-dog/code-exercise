package volatileDemo;

/**
 * 有关指令重排序的演示
 *
 * volatile 实习禁止指令重拍的优化，从而避免多线程环境下出现乱序执行的现象
 *
 * 先了解一个概念，内存屏障（Memory  Barrier) ， 是一个CPU指令，它的作用有2个：
 *  1.保证特定操作的执行顺序
 *  2.保证某些变量的内存可见性（利用该特性实现volatile的内存可见性）
 *      由于编译器和处理器都能进行指令重排优化。如果在指令间插入一条 Memory Barrier则会告诉编译器和
 *   CPU，不管什么指令都不能和这条Memory Barrier 指令重排序，也就是说  通过插入内存屏障禁止在内存
 *   屏障前后的指令执行重排序优化。 内存屏障另一个作用是强制刷出各种CPU的缓存数据，因此任何CPu上的线程
 *   都能读取到这些数据的最新版本。
 *
 *   工作内存与主内存同步延迟现象导致的可见性问题
 *      可以使用synchronized 或  volatile关键字解决，他们都可以使一个线程修改后的变量立即对其他线程可见。
 *     对于指令重排导致的可见性问题和有序性问题
 *     可以利用volatile关键字解决，因为  volatile的另一个作用就是禁止重排序优化
 * @author dlf
 * @date 2021/2/12 12:16
 */
public class ReSortSeqDemo {

    int a = 0;
    boolean flag = false;

    public void method1(){
        // 语句1
        a = -1;
        //语句2
        flag = true;
    }

    // 多线程环境中线程交替执行，由于编译器优化重排存在差异
    //两个线程中使用的变量能否保证一致是无法确定的，结果无法预测

    public void method2(){
        if (flag){
            // 语句3
            a = a + 5;
            System.out.printf("****retValue: "+ a);
        }
    }
}
