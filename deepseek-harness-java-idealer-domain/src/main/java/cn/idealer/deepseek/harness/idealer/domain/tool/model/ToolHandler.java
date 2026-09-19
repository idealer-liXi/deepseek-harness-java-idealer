package cn.idealer.deepseek.harness.idealer.domain.tool.model;

import cn.idealer.deepseek.harness.idealer.types.model.ToolResultBlock;

import java.util.concurrent.CompletionStage;

@FunctionalInterface
public interface ToolHandler {
    CompletionStage<ToolResultBlock> execute(String callId, String arguments);
}

