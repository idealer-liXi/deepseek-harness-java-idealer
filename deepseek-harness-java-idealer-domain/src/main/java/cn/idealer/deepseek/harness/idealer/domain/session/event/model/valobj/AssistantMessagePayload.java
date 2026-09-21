package cn.idealer.deepseek.harness.idealer.domain.session.event.model.valobj;

import cn.idealer.deepseek.harness.idealer.domain.llm.model.valobj.TokenUsage;
import cn.idealer.deepseek.harness.idealer.types.model.entity.Message;

/**
 * AssistantMessagePayload 表示AssistantMessagePayload消息。
 */
public record AssistantMessagePayload(
        long turn,
        long step,
        Message message,
        TokenUsage usage
) {}
