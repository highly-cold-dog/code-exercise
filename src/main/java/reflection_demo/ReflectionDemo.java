package reflection_demo;

import java.lang.reflect.Method;

/**
 * 有关Java反射
 *
 * @author dlf
 * @date 2021/5/18 0:33
 */
public class ReflectionDemo {
    public static void main(String[] args) {

        // 第一种获取类的方式，通过静态方法
        try {
            //需要指定绝对路径
            Object clazz = Class.forName("reflection_demo.Person");
            System.err.println(((Class<?>) clazz).getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //第二种获取类的方式
        Class clazz2 = Person.class;
        System.out.println("method2:" + clazz2.getName());

        //第三种方式 通过对象
        Person person = new Person();
        Class clazz3 = person.getClass();
        System.out.println("method3:" + clazz3.getName());

        person.setUserName("dlf");
        Object[] arams  = new Object[1];
      //  arams.
        myInvokeMethod(arams,clazz3,person,"toString");
    }

    /**
     * @param params 形参列表
     * @param clazz
     * @param obj
     */
    public static void myInvokeMethod(Object[] params, Class clazz, Object obj, String methodName) {
        Class[] aramsClass = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
           // aramsClass[i] = params[i].getClass();
            try {
                Method method = clazz.getMethod(methodName, aramsClass);
                Object object = method.invoke(obj, params);
                System.out.println(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}

class Person {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
