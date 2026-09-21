package cn.idealer.deepseek.harness.idealer.cases.agent;


import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;

public interface IAgentUseCase {

    /**
     * 向 Agent 发送消息；如果 Agent 不存在则先创建。
     *
     * @param request Agent 消息发送命令
     * @return Agent 消息发送结果
     */
    AgentMessageResponseDTO sendMessage(AgentMessageRequestDTO request);
}
