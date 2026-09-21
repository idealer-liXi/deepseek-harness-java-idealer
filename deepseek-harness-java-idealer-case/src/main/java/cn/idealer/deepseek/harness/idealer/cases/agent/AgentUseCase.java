package cn.idealer.deepseek.harness.idealer.cases.agent;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;
import cn.idealer.deepseek.harness.idealer.cases.agent.factory.AgentMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AgentUseCase implements IAgentUseCase{
    private static final Logger log = LoggerFactory.getLogger(AgentUseCase.class);

    @Autowired
    private AgentMessageFactory agentMessageFactory;

    @Override
    public AgentMessageResponseDTO sendMessage(AgentMessageRequestDTO request) {
        log.info("[UseCase] Agent={} 收到消息发送请求（channel={} streaming=false）",
                request.agentId(), request.channelCode());
        return agentMessageFactory.pipeline().execute(request);
    }
}
