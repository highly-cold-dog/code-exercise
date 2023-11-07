package juc.thread.atomic_reference_demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 *
 * @author dlf
 * @date 2021/3/4 23:11
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User u1 = new User("zs", 20);
        User u2 = new User("li4", 22);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        //主物理内存共享变量为张三
        atomicReference.set(u1);

        boolean result = atomicReference.compareAndSet(u1, u2);
        System.err.println(result);

        System.out.println("修改后:" + atomicReference.get().toString());

        System.out.println(atomicReference.compareAndSet(u1, u2) + "\t"
                + atomicReference.get().toString());


    }
}

class User {
    private String userName;

    private int age;

    public User(String zs, int i) {
        this.age = i;
        this.userName = zs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
