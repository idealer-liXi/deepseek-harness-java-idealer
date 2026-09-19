package cn.idealer.deepseek.harness.idealer.types.model;

public record ToolCallBlock(String callId, String name, String arguments) implements ContentBlock {
    public ToolCallBlock {
        if (callId == null || callId.isBlank()) throw new IllegalArgumentException("callId is required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        arguments = arguments == null ? "{}" : arguments;
    }
}

