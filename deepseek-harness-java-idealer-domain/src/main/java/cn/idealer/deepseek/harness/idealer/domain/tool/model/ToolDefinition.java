package cn.idealer.deepseek.harness.idealer.domain.tool.model;

import cn.idealer.deepseek.harness.idealer.types.model.ToolSchema;

public record ToolDefinition(ToolSchema schema, ToolHandler handler) {
}

