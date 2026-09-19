package cn.idealer.deepseek.harness.idealer.domain.agent.service;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentStatus;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.InboxTarget;
import cn.idealer.deepseek.harness.idealer.domain.session.port.SessionLog;
import cn.idealer.deepseek.harness.idealer.types.model.Message;

import java.util.concurrent.CompletableFuture;

public interface Agent {
    String id();
    AgentOptions options();
    AgentStatus status();
    Inbox inbox();
    SessionLog session();
    void send(Message message, InboxTarget target, boolean wakeup);
    CompletableFuture<Void> whenIdle();
    void cancel();

    default void followup(Message message) {
        send(message, InboxTarget.NEXT_TURN, true);
    }
}

