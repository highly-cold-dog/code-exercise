package patameter_passing;

import java.util.Arrays;

/**
 * 方法的参数传递
 *
 * @author dlf
 * @date 2020/12/29 23:20
 */
public class Exam4 {
    public static void main(String[] args) {
        int i = 1;

        String str = "hello";

        Integer num = 200;

        int[] arr = {1, 2, 3, 4, 5};

        MyData myData = new MyData();

        change(i, str, num, arr, myData);

        // i = 2
        System.out.println("i = " + i);
        // str = hello world
        System.out.println("str = " + str);
        // num = 3
        System.out.println("num = " + num);
        // arr = 2
        System.out.println("arr = " + Arrays.toString(arr));
        // a = 11
        System.out.println("myData.a = " + myData.a);


    }

    public static void change(int j, String s, Integer n, int[] a, MyData myData) {
        j += 1;
        System.out.println("j = " + j);
        s += "world";
        n += 1;
        a[0] += 1;
        myData.a += 1;
    }
}

class MyData {
    int a = 10;
}
