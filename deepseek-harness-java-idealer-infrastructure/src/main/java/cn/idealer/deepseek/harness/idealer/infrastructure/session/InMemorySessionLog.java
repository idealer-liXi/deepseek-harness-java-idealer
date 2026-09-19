package cn.idealer.deepseek.harness.idealer.infrastructure.session;

import cn.idealer.deepseek.harness.idealer.domain.session.model.SessionEvent;
import cn.idealer.deepseek.harness.idealer.domain.session.port.SessionLog;
import cn.idealer.deepseek.harness.idealer.types.model.Message;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class InMemorySessionLog implements SessionLog {
    private final List<SessionEvent> events = new ArrayList<>();

    @Override
    public synchronized SessionEvent append(String type, Map<String, Object> data) {
        SessionEvent event = new SessionEvent(events.size() + 1L, Instant.now(), type, data);
        events.add(event);
        return event;
    }

    @Override public synchronized List<SessionEvent> events() { return List.copyOf(events); }

    @Override
    public synchronized List<Message> deriveMessages() {
        // TODO：把 user/message、assistant/message、tool/result 事件投影为模型消息。
        return List.of();
    }
}

