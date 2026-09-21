package cn.idealer.deepseek.harness.idealer.domain.tool.port;

import cn.idealer.deepseek.harness.idealer.domain.tool.model.ToolDefinition;

import java.util.List;
import java.util.Optional;

public interface ToolRegistry {
    void register(ToolDefinition definition);
    Optional<ToolDefinition> find(String name);
    List<ToolSchema> schemas();
}

