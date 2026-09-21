package cn.idealer.deepseek.harness.idealer.app;

import cn.idealer.deepseek.harness.idealer.api.IAgentApi;
import cn.idealer.deepseek.harness.idealer.domain.agent.port.AgentRunLifecycle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "cn.idealer.deepseek.harness.idealer")
public class IdealerApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdealerApplication.class, args);
    }

    @Bean
    IAgentApi agentApi(AgentRunLifecycle lifecycle) {
        return new AgentMessageService(lifecycle);
    }
}

