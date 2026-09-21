package cn.idealer.deepseek.harness.idealer.domain.tool.service;

public class ToolCallExecutor {

    public record ToolLifecycleEvent(
            String phase,
            String callId,
            String toolName,
            String args,
            String result,
            String status,
            Long durationMs
    ) {}
}
