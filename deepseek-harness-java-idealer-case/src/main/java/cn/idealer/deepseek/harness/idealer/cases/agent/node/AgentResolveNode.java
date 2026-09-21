package cn.idealer.deepseek.harness.idealer.cases.agent.node;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageRequestDTO;
import cn.idealer.deepseek.harness.idealer.api.dto.AgentMessageResponseDTO;
import cn.idealer.deepseek.harness.idealer.cases.agent.factory.AgentMessageDynamicContext;
import cn.idealer.deepseek.harness.idealer.cases.agent.factory.AgentMessageFactory;
import cn.idealer.deepseek.harness.idealer.cases.orchestration.StrategyHandler;
import cn.idealer.deepseek.harness.idealer.domain.agent.port.AgentRunLifecycle;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.run.AgentRun;
import cn.idealer.deepseek.harness.idealer.domain.sandbox.service.SandboxExtraRootsRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("agentMessageResolveNode")
public class AgentResolveNode extends AbstractAgentMessageNode{

    private static final Logger log = LoggerFactory.getLogger(AgentResolveNode.class);

    @Autowired
    private SandboxExtraRootsRegistry sandboxExtraRootsRegistry;

    @Autowired
    private AgentRunLifecycle agentFactory;

    @Override
    protected AgentMessageResponseDTO doApply(AgentMessageRequestDTO request,
                                              AgentMessageDynamicContext ctx) throws Exception {
        if (request.sandboxRoots() != null) {
            //用户请求中，授权一个新的可操作目录，将该可操作目录登记
            sandboxExtraRootsRegistry.update(request.sandboxRoots());
        }

        AgentRun agent = agentFactory.get(request.agentId());
        if(agent == null){

        }else{

        }


        return null;
    }

    @Override
    public StrategyHandler<AgentMessageRequestDTO, AgentMessageDynamicContext, AgentMessageResponseDTO> getNext(AgentMessageRequestDTO requestParameter, AgentMessageDynamicContext dynamicContext) throws Exception {
        return null;
    }
}
