package cn.idealer.deepseek.harness.idealer.infrastructure.tool;

import cn.idealer.deepseek.harness.idealer.domain.tool.model.ToolDefinition;
import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolRegistry;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public final class InMemoryToolRegistry implements ToolRegistry {
    private final Map<String, ToolDefinition> tools = new LinkedHashMap<>();

    @Override public synchronized void register(ToolDefinition definition) { tools.put(definition.schema().name(), definition); }
    @Override public synchronized Optional<ToolDefinition> find(String name) { return Optional.ofNullable(tools.get(name)); }
    @Override public synchronized List<ToolSchema> schemas() { return tools.values().stream().map(ToolDefinition::schema).toList(); }
}

