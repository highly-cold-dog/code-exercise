package jvm_demo;

import cn.hutool.core.util.ObjectUtil;
import step.TestStep;

/**
 * @author dlf
 * @date 2021/6/14 17:36
 */
public class HelloGC {
    public static void main(String[] args) throws Exception {
        TestStep testStep = new TestStep();
        System.err.println("testStep:" + ObjectUtil.isNotEmpty(testStep));
    }
}
