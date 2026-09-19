package cn.idealer.deepseek.harness.idealer.domain.llm.port;

import cn.idealer.deepseek.harness.idealer.domain.llm.model.GenerateRequest;
import cn.idealer.deepseek.harness.idealer.types.model.StreamChunk;

import java.util.concurrent.Flow;

public interface LlmRuntimePort {
    Flow.Publisher<StreamChunk> stream(GenerateRequest request);
}

