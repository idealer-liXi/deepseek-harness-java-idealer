package cn.idealer.deepseek.harness.idealer.domain.agent.service;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.valobj.InboxTarget;
import cn.idealer.deepseek.harness.idealer.types.model.entity.Message;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** 尚未越过 Agent 边界的输入；已处理历史属于 SessionLog。 */
public final class Inbox {
    private final Deque<Message> nextTurn = new ArrayDeque<>();
    private final Deque<Message> nextStep = new ArrayDeque<>();

    public synchronized void append(InboxTarget target, Message message) {
        queue(target).addLast(message);
    }

    public synchronized List<Message> claim(InboxTarget target) {
        Deque<Message> queue = queue(target);
        List<Message> claimed = new ArrayList<>(queue);
        queue.clear();
        return List.copyOf(claimed);
    }

    public synchronized boolean hasPending() {
        return !nextTurn.isEmpty() || !nextStep.isEmpty();
    }

    public synchronized int size(InboxTarget target) {
        return queue(target).size();
    }

    private Deque<Message> queue(InboxTarget target) {
        return target == InboxTarget.NEXT_TURN ? nextTurn : nextStep;
    }
}

