package cn.idealer.deepseek.harness.idealer.caseapp;

import cn.idealer.deepseek.harness.idealer.api.IAgentApi;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequest;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponse;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.port.AgentRunLifecycle;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.run.AgentRun;
import cn.idealer.deepseek.harness.idealer.types.model.Message;

import java.nio.file.Path;

/** 应用用例只编排领域对象，不实现 ReAct 算法。 */
public final class AgentMessageService implements IAgentApi {
    private final AgentRunLifecycle lifecycle;

    public AgentMessageService(AgentRunLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public AgentMessageResponse send(AgentMessageRequest request) {
        requireText(request.agentId(), "agentId");
        requireText(request.message(), "message");
        AgentOptions options = new AgentOptions(
                request.provider(), request.model(), request.maxTokens() == null ? 4096 : request.maxTokens());
        String cwd = request.cwd() == null || request.cwd().isBlank()
                ? Path.of(".").toAbsolutePath().normalize().toString()
                : request.cwd();
        AgentRun agent = lifecycle.getOrCreate(request.agentId(), options, cwd);
        agent.updateOptions(options);
        agent.followup(Message.user(request.message()));
        return new AgentMessageResponse(agent.id(), agent.status().name(), "ReAct runs asynchronously");
    }

    private static void requireText(String value, String name) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(name + " is required");
    }
}

