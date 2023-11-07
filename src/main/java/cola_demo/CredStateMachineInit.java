package cola_demo;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dlf
 * @date 2021/12/1 23:32
 */
@Component
public class CredStateMachineInit {


    public static final String machineOne = "one";


    /**
     * 可配置多个
     */
    @PostConstruct
    public void init() {
        configureOneMachine();
    }

    /**
     * 初始化状态机状态
     */
    public void configureOneMachine() {
        TestCola testCola = new TestCola();
        StateMachineBuilder<CredStatusEnum, CredEventEnum, CredStateMachineContext> builder
                = StateMachineBuilderFactory.create();
        //状态流转，外部流转
        builder.externalTransition()
                //待提交
                .from(CredStatusEnum.UNCOMMIT)
                //待审核
                .to(CredStatusEnum.UNCHECK)
                //提交事件
                .on(CredEventEnum.COMMIT)
                .perform(testCola.commit());
        builder.build(machineOne);

    }

}
