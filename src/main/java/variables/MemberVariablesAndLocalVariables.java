package variables;

/**
 * 成员变量与局部变量
 *
 * @author dlf
 * @date 2021/1/3 23:19
 */
public class MemberVariablesAndLocalVariables {
    //成员变量，类变量(有static修饰)
    static int s;
    // 成员变量，实例变量
    int i;
    //成员变量，实例变量
    int j;

    /**
     * 非静态代码块的执行
     */ {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;
    }


    public static void main(String[] args) {
         // s = 0 i = 0 j = 0
        MemberVariablesAndLocalVariables m1 = new MemberVariablesAndLocalVariables();
        // s =0 i = 0 j = 0
        MemberVariablesAndLocalVariables m2 = new MemberVariablesAndLocalVariables();

        m1.test(10);
        m1.test(20);
        m2.test(30);

        System.out.println("m1.i = " + m1.i + " m1.j= " + m1.j + " m1.s= " + m1.s);
        System.out.println("m2.i = " + m2.i + " m2.j= " + m2.j + " m2.s= " + m2.s);
    }
}
