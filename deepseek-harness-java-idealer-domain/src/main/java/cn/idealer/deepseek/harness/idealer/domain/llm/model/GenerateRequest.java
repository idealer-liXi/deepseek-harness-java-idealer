package cn.idealer.deepseek.harness.idealer.domain.llm.model;

import cn.idealer.deepseek.harness.idealer.types.model.Message;
import cn.idealer.deepseek.harness.idealer.types.model.ToolSchema;

import java.util.List;

public record GenerateRequest(
        String provider,
        String model,
        String systemPrompt,
        List<Message> messages,
        List<ToolSchema> tools,
        int maxTokens
) {
    public GenerateRequest {
        messages = List.copyOf(messages == null ? List.of() : messages);
        tools = List.copyOf(tools == null ? List.of() : tools);
    }
}

