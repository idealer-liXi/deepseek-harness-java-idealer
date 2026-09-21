package cn.idealer.deepseek.harness.idealer.domain.agent.model.entity;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.valobj.ApprovalModeVO;

/**
 *
 * @param channelCode 模型渠道编码
 * @param provider 模型供应商
 * @param model 模型
 * @param maxTokens 单次输出上限
 * @param reasoningEffort 推理强度
 * @param baseUrl 模型供应商url
 * @param apiKey 模型供应商key
 * @param protocol openAI / anthropic
 * @param approvalMode 审批模式
 */
public record AgentOptions(
        String channelCode,
        String provider,
        String model,
        Integer maxTokens,
        String reasoningEffort,
        String baseUrl,
        String apiKey,
        String protocol,
        ApprovalModeVO approvalMode
) {

    public AgentOptions(String channelCode, String provider, String model, Integer maxTokens,
                        String baseUrl, String apiKey, String protocol) {
        this(channelCode, provider, model, maxTokens, baseUrl, apiKey, protocol,
                null, ApprovalModeVO.REQUEST_APPROVAL);
    }

    public AgentOptions(String channelCode, String provider, String model, Integer maxTokens,
                        String reasoningEffort, String baseUrl, String apiKey, String protocol) {
        this(channelCode, provider, model, maxTokens, reasoningEffort, baseUrl, apiKey, protocol,
                ApprovalModeVO.REQUEST_APPROVAL);
    }

    public AgentOptions {
        if (maxTokens != null && maxTokens <= 0) {
            throw new IllegalArgumentException("maxTokens must be a positive integer");
        }
    }

    public AgentOptions withReasoningEffort(String value) {
        if (value == null || value.isBlank()) {
            return this;
        }
        return new AgentOptions(channelCode, provider, model, maxTokens,
                value.trim().toLowerCase(), baseUrl, apiKey, protocol, approvalMode);
    }

    public AgentOptions withApprovalMode(ApprovalModeVO mode) {
        if (mode == null) {
            return this;
        }
        return new AgentOptions(channelCode, provider, model, maxTokens,
                reasoningEffort, baseUrl, apiKey, protocol, mode);
    }

}
