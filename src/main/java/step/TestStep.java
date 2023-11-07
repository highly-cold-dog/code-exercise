package step;

/**
 * @author dlf
 * @date 2020/12/30 0:06
 */
public class TestStep {

    public static void main(String[] args) throws IllegalAccessException {
        System.err.println(getStep(3));
    }


    // 实现f(n): 求n步台阶，一共有几种走法
    public static int getStep(int n) throws IllegalAccessException {
        if (n < 1) {
            throw new IllegalAccessException(n + "不能小于1");
        }
        // 一步
        if (1 == n || 2 == n) {
            return n;
        }
        return getStep(n - 2) + getStep(n - 1);

    }
}
