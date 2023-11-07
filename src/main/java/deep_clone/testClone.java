package deep_clone;


import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 反射工具类进行对象拷贝，支持深拷贝
 * @author dlf
 * @date 2023/9/15 17:02
 */
public class testClone {
    public static void main(String[] args) throws Exception {

        CaptureStatisticsCompany a = new CaptureStatisticsCompany();
        a.setEntInfoCount(1L);
        a.setEntUuid("test01");
        List<CaptureStatisticsCompany> list1 = Arrays.asList(a);
        System.out.println("a:"+a);
        CaptureStatisticsCompany b = new CaptureStatisticsCompany();
        b.setEntUuid("test01");
        b.setApmEntInfoCount(2L);
        System.out.println("b:"+b);
        List<CaptureStatisticsCompany> list2 = Arrays.asList(b);

        System.out.println("list2:"+list2);
        List<CaptureStatisticsCompany> BeanUtilsResult = new ArrayList<>();

        List<CaptureStatisticsCompany> result = new ArrayList<>();
        //单纯使用BeanUtils.copy进行拷贝
       /* for (CaptureStatisticsCompany company : list1) {

            for (CaptureStatisticsCompany s : list2) {

                if (company.getEntUuid().equals(s.getEntUuid())) {
                    try {
                        //处理浅拷贝失效问题
                        BeanUtils.copyProperties(company,s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            BeanUtilsResult.add(company);
        }
        System.err.println(" BeanUtils.copyProperties："+BeanUtilsResult);*/

        for (CaptureStatisticsCompany company : list1) {
            CaptureStatisticsCompany captureStatisticsCompany = BeanUtil.copyProperties(company, CaptureStatisticsCompany.class);
            for (CaptureStatisticsCompany s : list2) {

                if (company.getEntUuid().equals(s.getEntUuid())) {
                    try {
                        //处理浅拷贝失效问题
                        captureStatisticsCompany = (CaptureStatisticsCompany) setValues(objectToMap(s), company);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            result.add(captureStatisticsCompany);
        }


        /**
         *    // 修改person2的属性，不会影响person1
         *         person2.name = "Bob";
         *         person2.address.street = "456 Broadway";
         */



        System.out.println();

        System.err.println("result:" + result);

    }


    public static HashMap<String, Object> objectToMap(Object obj) {
        HashMap<String, Object> map = new HashMap<>();

        // 获取对象的所有字段
        Field[] fields = obj.getClass().getDeclaredFields();

        // 遍历每个字段，将其添加到Map中
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Object fieldValue = field.get(obj);
                if (null != fieldValue) {
                    map.put(fieldName, fieldValue);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }


    public static Object setValues(HashMap<String, Object> map, Object company) throws Exception {
        System.out.println("map"+map);
        // 获取CaptureStatisticsCompany类的所有字段
        Field[] fields = CaptureStatisticsCompany.class.getDeclaredFields();

        // 遍历所有字段
        for (Field field : fields) {
            // 获取字段名
            String fieldName = field.getName();

            // 如果map中包含该字段名的键
            if (map.containsKey(fieldName) && !"serialVersionUID".equals(fieldName)) {
                // 设置字段可访问，以便对私有字段进行操作
                field.setAccessible(true);

                // 获取map中该字段名对应的值
                Object value = map.get(fieldName);

                // 将值赋给company对象的该字段
                field.set(company, value);
            }
        }

        // 返回赋值后的company对象
        System.out.println("company"+company);
        return company;
    }
}
