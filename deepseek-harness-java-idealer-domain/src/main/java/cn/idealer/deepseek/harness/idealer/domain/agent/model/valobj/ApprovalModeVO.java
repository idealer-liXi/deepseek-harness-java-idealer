package cn.idealer.deepseek.harness.idealer.domain.agent.model.valobj;

/**
 * ApprovalModeVO 表示一次智能体会话的运行审批模式。
 */
public enum ApprovalModeVO {
    /**
     * 请求审批
     */
    REQUEST_APPROVAL,
    /**
     * 自动审批
     */
    AUTO_APPROVE,
    /**
     * 全部权限
     */
    FULL_OPEN;

    public static ApprovalModeVO from(String value) {
        if (value == null || value.isBlank()) {
            return REQUEST_APPROVAL;
        }
        return valueOf(value.trim().toUpperCase().replace('-', '_'));
    }
}
