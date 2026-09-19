package cn.idealer.deepseek.harness.idealer.domain.session.model;

import java.time.Instant;
import java.util.Map;

public record SessionEvent(long sequence, Instant time, String type, Map<String, Object> data) {
    public SessionEvent {
        data = Map.copyOf(data == null ? Map.of() : data);
    }
}

