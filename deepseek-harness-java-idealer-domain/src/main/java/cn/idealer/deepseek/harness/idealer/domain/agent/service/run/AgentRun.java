package cn.idealer.deepseek.harness.idealer.domain.agent.service.run;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.Agent;

public interface AgentRun extends Agent {
    void updateOptions(AgentOptions options);
}

