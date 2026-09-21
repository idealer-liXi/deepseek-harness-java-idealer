package cn.idealer.deepseek.harness.idealer.cases.agent.node;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;
import cn.idealer.deepseek.harness.idealer.cases.agent.factory.AgentMessageDynamicContext;
import cn.idealer.deepseek.harness.idealer.cases.orchestration.AbstractStrategyRouter;
import cn.idealer.deepseek.harness.idealer.cases.orchestration.StrategyHandler;

public abstract class AbstractAgentMessageNode extends AbstractStrategyRouter<AgentMessageRequestDTO, AgentMessageDynamicContext, AgentMessageResponseDTO> {

}
