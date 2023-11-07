package cola_demo;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 状态机流转的测试
 *
 * @author dlf
 * @date 2021/12/3 1:04
 */
public class TestCola {

    Action<CredStatusEnum, CredEventEnum, CredStateMachineContext> commit() {
        return (credStatusEnum, s1, credEventEnum, credStateMachineContext) -> {
            System.err.println("测试流程");
        };
    }
    public static void main(String[] args) {

        CredStateMachineContext credStateMachineContext = new CredStateMachineContext();
        credStateMachineContext.setCredId(003L);
        credStateMachineContext.setAdvise("test01");
        credStateMachineContext.setAuditId(003L);
        StateMachineFactory.get("one")
                .fireEvent(CredStatusEnum.UNCOMMIT, CredStatusEnum.UNCHECK, credStateMachineContext);

    }
}
