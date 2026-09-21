package cn.idealer.deepseek.harness.idealer.domain.agent.port;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.entity.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.run.AgentRun;

public interface AgentRunLifecycle {
    AgentRun getOrCreate(String agentId, AgentOptions options, String cwd);
    AgentRun get(String agentId);
}

