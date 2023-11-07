package cola_demo;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.State;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;

/**
 * 状态机的测试
 *
 * @author dlf
 * @date 2021/12/3 1:22
 */
public class StateMachineTest {

    static String MACHINE_ID = "StateMachineTest";

    static enum States {
        STATE1, STATE2, STATE3, STATE4;
    }

    static enum Events {
        EVENT1, EVENT2, EVENT3, EVENT4;
    }

    static class Context {
        String operate = "dlf";
        String userId = "003";
    }


    public static void main(String[] args) {
        //第一步生成一个状态机builder
        StateMachineBuilder builder = StateMachineBuilderFactory.create();
        //第二步设置一个外部状态转移类型
        builder.externalTransition().from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .perform(commit());
        StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID);

        //触发
        Context context = new Context();
        StateMachineFactory.get(MACHINE_ID)
                .fireEvent(CredStatusEnum.UNCOMMIT, CredEventEnum.COMMIT, context);

    }

    static Action<CredStatusEnum, CredEventEnum, CredStateMachineContext> commit() {
        return (credStatusEnum, s1, credEventEnum, credStateMachineContext) -> {
            System.err.println("测试流程");
        };

    }

}
