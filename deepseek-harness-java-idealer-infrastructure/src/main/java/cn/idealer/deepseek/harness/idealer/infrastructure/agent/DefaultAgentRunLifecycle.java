package cn.idealer.deepseek.harness.idealer.infrastructure.agent;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.entity.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.port.AgentRunLifecycle;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.Inbox;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.run.AgentRun;
import cn.idealer.deepseek.harness.idealer.domain.prompt.port.SystemPromptAssembler;
import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolCallExecutor;
import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public final class DefaultAgentRunLifecycle implements AgentRunLifecycle {
    private final ConcurrentMap<String, AgentRun> agents = new ConcurrentHashMap<>();
    private final LlmRuntimePort llm;
    private final ToolRegistry tools;
    private final ToolCallExecutor toolExecutor;
    private final SystemPromptAssembler promptAssembler;

    public DefaultAgentRunLifecycle(LlmRuntimePort llm, ToolRegistry tools, ToolCallExecutor toolExecutor,
                                    SystemPromptAssembler promptAssembler) {
        this.llm = llm;
        this.tools = tools;
        this.toolExecutor = toolExecutor;
        this.promptAssembler = promptAssembler;
    }

    @Override
    public AgentRun getOrCreate(String agentId, AgentOptions options, String cwd) {
        return agents.computeIfAbsent(agentId, ignored -> new ReactLoopAgent(
                agentId, options, cwd, new Inbox(), new InMemorySessionLog(),
                llm, tools, toolExecutor, promptAssembler));
    }

    @Override public AgentRun get(String agentId) { return agents.get(agentId); }
}

