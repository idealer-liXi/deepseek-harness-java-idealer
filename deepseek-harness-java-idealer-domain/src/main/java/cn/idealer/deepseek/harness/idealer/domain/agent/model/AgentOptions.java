package cn.idealer.deepseek.harness.idealer.domain.agent.model;

public record AgentOptions(String provider, String model, int maxTokens) {
    public AgentOptions {
        provider = provider == null ? "" : provider;
        model = model == null ? "" : model;
        if (maxTokens <= 0) maxTokens = 4096;
    }
}

