package cn.idealer.deepseek.harness.idealer.domain.agent.service.run;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.AgentStatus;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.InboxTarget;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.Phase;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.Inbox;
import cn.idealer.deepseek.harness.idealer.domain.llm.port.LlmRuntimePort;
import cn.idealer.deepseek.harness.idealer.domain.prompt.port.SystemPromptAssembler;
import cn.idealer.deepseek.harness.idealer.domain.session.port.SessionLog;
import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolCallExecutor;
import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolRegistry;
import cn.idealer.deepseek.harness.idealer.types.model.Message;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ReAct 学习骨架。建议按 send → wakeDriver → kick → turn → step 的顺序实现。
 */
public final class ReactLoopAgent implements AgentRun {
    private final String id;
    private volatile AgentOptions options;
    private final String cwd;
    private final Inbox inbox;
    private final SessionLog session;
    private final LlmRuntimePort llm;
    private final ToolRegistry toolRegistry;
    private final ToolCallExecutor toolExecutor;
    private final SystemPromptAssembler promptAssembler;
    private volatile Phase phase = new Phase.Idle(0);
    private volatile CompletableFuture<Void> activity = CompletableFuture.completedFuture(null);

    public ReactLoopAgent(
            String id,
            AgentOptions options,
            String cwd,
            Inbox inbox,
            SessionLog session,
            LlmRuntimePort llm,
            ToolRegistry toolRegistry,
            ToolCallExecutor toolExecutor,
            SystemPromptAssembler promptAssembler
    ) {
        this.id = Objects.requireNonNull(id);
        this.options = Objects.requireNonNull(options);
        this.cwd = Objects.requireNonNull(cwd);
        this.inbox = Objects.requireNonNull(inbox);
        this.session = Objects.requireNonNull(session);
        this.llm = Objects.requireNonNull(llm);
        this.toolRegistry = Objects.requireNonNull(toolRegistry);
        this.toolExecutor = Objects.requireNonNull(toolExecutor);
        this.promptAssembler = Objects.requireNonNull(promptAssembler);
    }

    @Override public String id() { return id; }
    @Override public AgentOptions options() { return options; }
    @Override public Inbox inbox() { return inbox; }
    @Override public SessionLog session() { return session; }

    @Override
    public AgentStatus status() {
        if (phase instanceof Phase.Running) return AgentStatus.RUNNING;
        if (phase instanceof Phase.Maintenance) return AgentStatus.MAINTENANCE;
        if (phase instanceof Phase.Disposed) return AgentStatus.DISPOSED;
        return AgentStatus.IDLE;
    }

    @Override
    public synchronized void send(Message message, InboxTarget target, boolean wakeup) {
        inbox.append(target, message);
        if (wakeup) wakeDriver();
    }

    private synchronized void wakeDriver() {
        if (!(phase instanceof Phase.Idle idle) || !inbox.hasPending()) return;
        Phase.Running running = new Phase.Running(new AtomicBoolean(false), idle.lastTurn(), 0);
        phase = running;
        activity = CompletableFuture.runAsync(() -> kick(running));
    }

    private void kick(Phase.Running running) {
        try {
            // TODO 1：循环执行 turn，直到 Inbox 为空、取消或发生错误。
            throw new UnsupportedOperationException("TODO: implement ReAct driver in kick/turn/step");
        } finally {
            synchronized (this) {
                phase = new Phase.Idle(running.turn());
            }
        }
    }

    @SuppressWarnings("unused")
    private boolean turn(Phase.Running running) {
        // TODO 2：claim NEXT_TURN；记录 turn/step 事件；循环调用 step。
        throw new UnsupportedOperationException("TODO: implement turn");
    }

    @SuppressWarnings("unused")
    private Object step(Phase.Running running, long turn, long step) {
        // TODO 3：assemble prompt → build request → llm.stream → tool calls → toolExecutor。
        // 可用依赖：cwd、session、llm、toolRegistry、toolExecutor、promptAssembler。
        throw new UnsupportedOperationException("TODO: implement step");
    }

    @Override public CompletableFuture<Void> whenIdle() { return activity; }

    @Override
    public synchronized void cancel() {
        if (phase instanceof Phase.Running running) running.aborted().set(true);
    }

    @Override public void updateOptions(AgentOptions options) { this.options = Objects.requireNonNull(options); }
}

