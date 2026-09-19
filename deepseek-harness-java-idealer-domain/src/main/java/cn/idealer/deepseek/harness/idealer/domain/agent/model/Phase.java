package cn.idealer.deepseek.harness.idealer.domain.agent.model;

import java.util.concurrent.atomic.AtomicBoolean;

public sealed interface Phase permits Phase.Idle, Phase.Running, Phase.Maintenance, Phase.Disposed {
    record Idle(long lastTurn) implements Phase {}
    record Running(AtomicBoolean aborted, long turn, long step) implements Phase {}
    record Maintenance(long lastTurn) implements Phase {}
    record Disposed(long lastTurn) implements Phase {}
}

