package cn.idealer.deepseek.harness.idealer.types.model;

public record ToolResultBlock(String callId, String content, boolean success) implements ContentBlock {
    public ToolResultBlock {
        if (callId == null || callId.isBlank()) throw new IllegalArgumentException("callId is required");
        content = content == null ? "" : content;
    }
}

