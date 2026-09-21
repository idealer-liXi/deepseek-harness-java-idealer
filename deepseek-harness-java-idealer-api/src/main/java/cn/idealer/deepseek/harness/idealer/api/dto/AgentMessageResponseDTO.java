package cn.idealer.deepseek.harness.idealer.api.dto;

/**
 * Agent 消息发送响应。
 *
 * @param agentId Agent ID
 * @param sessionId 会话 ID
 * @param status Agent 状态，例如 idle、running
 * @param messages 会话消息
 *
 */
public record AgentMessageResponseDTO(String agentId,
                                      String sessionId,
                                      String status,
                                      String messages) {
}

