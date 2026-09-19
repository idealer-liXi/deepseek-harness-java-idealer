package cn.idealer.deepseek.harness.idealer.types.model;

/** 模型消息中的一个内容块。 */
public sealed interface ContentBlock permits TextBlock, ToolCallBlock, ToolResultBlock {
}

