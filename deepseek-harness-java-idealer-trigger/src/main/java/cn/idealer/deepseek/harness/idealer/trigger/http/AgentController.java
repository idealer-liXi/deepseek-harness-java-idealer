package cn.idealer.deepseek.harness.idealer.trigger.http;

import cn.idealer.deepseek.harness.idealer.api.IAgentApi;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequest;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agents")
public final class AgentController {
    private final IAgentApi IAgentApi;

    public AgentController(IAgentApi IAgentApi) { this.IAgentApi = IAgentApi; }

    @PostMapping("/messages")
    public AgentMessageResponse send(@RequestBody AgentMessageRequest request) {
        return IAgentApi.send(request);
    }
}

