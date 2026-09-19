package cn.idealer.deepseek.harness.idealer.domain.session.port;

import cn.idealer.deepseek.harness.idealer.domain.session.model.SessionEvent;
import cn.idealer.deepseek.harness.idealer.types.model.Message;

import java.util.List;
import java.util.Map;

public interface SessionLog {
    SessionEvent append(String type, Map<String, Object> data);
    List<SessionEvent> events();
    List<Message> deriveMessages();
}

