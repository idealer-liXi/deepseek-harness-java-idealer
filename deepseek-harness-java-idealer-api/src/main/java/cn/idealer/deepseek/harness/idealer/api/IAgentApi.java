package cn.idealer.deepseek.harness.idealer.api;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequest;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponse;

public interface IAgentApi {
    AgentMessageResponse send(AgentMessageRequest request);
}

