package cn.idealer.deepseek.harness.idealer.domain.prompt.port;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentOptions;
import cn.idealer.deepseek.harness.idealer.types.model.ToolSchema;

import java.util.List;

public interface SystemPromptAssembler {
    String assemble(String agentId, AgentOptions options, String cwd, List<ToolSchema> tools);
}

