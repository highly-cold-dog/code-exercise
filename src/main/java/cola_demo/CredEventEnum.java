package cola_demo;

/**
 * 状态机的事件枚举类
 *
 * @author dlf
 * @date 2021/12/1 23:27
 */
public enum CredEventEnum {

    //提交
    COMMIT,

    //审核同意
    AUDIT_AGREE,

    //审核不同意/退回
    AUDIT_DISAGREE;
}
