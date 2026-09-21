package cn.idealer.deepseek.harness.idealer.trigger.http;

import cn.idealer.deepseek.harness.idealer.api.IAgentApi;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;
import cn.idealer.deepseek.harness.idealer.api.response.Response;
import cn.idealer.deepseek.harness.idealer.cases.agent.IAgentUseCase;
import org.springframework.stereotype.Service;


@Service
public final class AgentApi implements IAgentApi {
    private final IAgentUseCase agentUseCase;

    /**
     * 处理Agent API。
     * 流程：转换协议 → 调用应用门面 → 返回结果。
     * @param agentUseCase Agent Use Case参数。
     * @return 方法执行结果。
     */
    public AgentApi(IAgentUseCase agentUseCase) {
        this.agentUseCase = agentUseCase;
    }


    @Override
    public Response<AgentMessageResponseDTO> sendMessage(AgentMessageRequestDTO request) {
        return execute(() -> agentUseCase.sendMessage(request));
    }

    private <T> Response<T> execute(java.util.function.Supplier<T> action) {
        try {
            return Response.success(action.get());
        } catch (IllegalArgumentException exception) {
            return Response.invalidArgument(exception.getMessage());
        } catch (RuntimeException exception) {
            return Response.internalError(exception.getMessage());
        }
    }

}

