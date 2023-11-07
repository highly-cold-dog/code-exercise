package classinit;

/**
 * 类初始化
 * 父类初始化<clint>
 * (1) j = method()
 * (1) 父类静态代码块
 *
 * @author dlf
 * @date 2020/12/27 21:07
 */
public class Father {
    private int i = test();
    private static int j = method();

    public int test() {
        System.out.println("(1)");
        return 1;
    }

    Father() {
        System.out.println("(2)");
    }

    {
        System.out.println("(3)");
    }

    public static int method() {
        System.out.println("(5)");
        return 1;
    }

}
