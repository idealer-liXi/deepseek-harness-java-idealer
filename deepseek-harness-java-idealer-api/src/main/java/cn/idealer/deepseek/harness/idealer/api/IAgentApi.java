package cn.idealer.deepseek.harness.idealer.api;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;
import cn.idealer.deepseek.harness.idealer.api.response.Response;

public interface IAgentApi {
    Response<AgentMessageResponseDTO> sendMessage(AgentMessageRequestDTO request);
}

