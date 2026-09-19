package cn.idealer.deepseek.harness.idealer.types.model;

import java.util.Map;

public record ToolSchema(String name, String description, Map<String, Object> parameters) {
    public ToolSchema {
        parameters = Map.copyOf(parameters == null ? Map.of() : parameters);
    }
}

