package singletontest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * 给定一个只包含大写英文字母的字符串S，要求你给出对S重新排列的所有不同的排列数
 * 如： S为ABA   则不同的排列数为     ABA,AAB,BAA三种
 *
 * @author dlf
 * @date 2021/5/26 11:59
 */
public class Test1 {
    public static void main(String[] args) throws ParseException {
        // getSingleTon();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("181230");
        System.out.println(date);
    }

    // 获取数组中单独出现的元素
    public static int getSingleTon() {
        int[] ints = {1, 1, 3, 4, 4, 2, 2};
        //先进行简单的排序
        Arrays.sort(ints);
        int result;
        //做对比
        for (int i = 1; i < ints.length; i++) {
            //其实这里要注意的是需要防止数组下表越界的写法
            //以首元素作为标准
            int beforeInt = ints[0];
            int nextInt = ints[i + 1];
            //  这个逻辑是有问题的

        }
        return 1;
    }

    public static String testScan() {
        //接收键盘输入
        Scanner input = new Scanner(System.in);
        // 将键盘输入的字符转化为大写字符
        String str = input.nextLine().toUpperCase(Locale.ROOT);
        System.out.println(str);
        //输入的字符串的长度
        int strLength = str.length();
        return str;
    }
}
