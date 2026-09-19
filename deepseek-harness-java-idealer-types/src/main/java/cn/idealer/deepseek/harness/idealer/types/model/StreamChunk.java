package cn.idealer.deepseek.harness.idealer.types.model;

/** LLM 流式输出的最小协议；可在练习中扩展 reasoning、usage 和 error。 */
public sealed interface StreamChunk permits StreamChunk.TextDelta, StreamChunk.ToolCall, StreamChunk.Finished {
    record TextDelta(String text) implements StreamChunk {}
    record ToolCall(ToolCallBlock call) implements StreamChunk {}
    record Finished(FinishReason reason) implements StreamChunk {}

    enum FinishReason { COMPLETED, TOOL_CALLS, MAX_TOKENS, ERROR }
}

