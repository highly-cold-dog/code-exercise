package cola_demo;

import lombok.Data;

import java.util.List;

/**
 * 状态机上下文
 *
 * @author dlf
 * @date 2021/12/1 23:29
 */

@Data
public class CredStateMachineContext {

    private Long credId;

    private List<String> audits;

    private Long auditId;

    private String advise;

}
