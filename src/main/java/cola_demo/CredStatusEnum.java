package cola_demo;

/**
 * 状态的枚举类
 *
 * @author dlf
 * @date 2021/12/1 23:25
 */
public enum CredStatusEnum {
    //未提交
    UNCOMMIT,
    //待审核
    UNCHECK,
    //已审核
    CHECKED,
    //被退回
    REHECT;
}
