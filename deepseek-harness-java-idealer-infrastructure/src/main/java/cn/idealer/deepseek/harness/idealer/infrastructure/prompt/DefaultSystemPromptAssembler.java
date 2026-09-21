package cn.idealer.deepseek.harness.idealer.infrastructure.prompt;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.entity.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.prompt.port.SystemPromptAssembler;
import cn.idealer.deepseek.harness.idealer.types.model.valobj.ToolSchema;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class DefaultSystemPromptAssembler implements SystemPromptAssembler {
    @Override
    public String assemble(String agentId, AgentOptions options, String cwd, List<ToolSchema> tools) {
        return "You are a learning agent. Working directory: " + cwd;
    }
}

