package cn.idealer.deepseek.harness.idealer.infrastructure.llm;

import cn.idealer.deepseek.harness.idealer.domain.llm.model.GenerateRequest;
import cn.idealer.deepseek.harness.idealer.domain.llm.port.LlmRuntimePort;
import cn.idealer.deepseek.harness.idealer.types.model.StreamChunk;
import org.springframework.stereotype.Component;

import java.util.concurrent.Flow;

@Component
public final class StubLlmRuntimeAdapter implements LlmRuntimePort {
    @Override
    public Flow.Publisher<StreamChunk> stream(GenerateRequest request) {
        return subscriber -> subscriber.onSubscribe(new Flow.Subscription() {
            private boolean done;
            @Override public void request(long n) {
                if (done) return;
                done = true;
                subscriber.onError(new UnsupportedOperationException("TODO: replace StubLlmRuntimeAdapter"));
            }
            @Override public void cancel() { done = true; }
        });
    }
}

