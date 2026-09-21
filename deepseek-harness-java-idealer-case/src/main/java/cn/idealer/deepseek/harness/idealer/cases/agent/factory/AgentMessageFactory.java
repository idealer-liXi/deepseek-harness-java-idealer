package cn.idealer.deepseek.harness.idealer.cases.agent.factory;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;
import cn.idealer.deepseek.harness.idealer.cases.agent.node.AgentResolveNode;
import cn.idealer.deepseek.harness.idealer.cases.orchestration.CasePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentMessageFactory {

    @Autowired
    private AgentResolveNode rootNode;

    public CasePipeline<AgentMessageRequestDTO, AgentMessageDynamicContext,AgentMessageResponseDTO> pipeline(){
        return CasePipeline.of("Agent Message", rootNode, AgentMessageDynamicContext::new);
    }

}
