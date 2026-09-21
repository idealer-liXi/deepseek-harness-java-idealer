package cn.idealer.deepseek.harness.idealer.domain.agent.model.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public sealed interface Phase permits Phase.Idle, Phase.Running, Phase.Maintenance {
    /** 空闲阶段：没有正在执行的工作，记录最近完成轮次。 */
    record Idle(long lastTurn) implements Phase {}

    /** 维护阶段：非对话轮次的维护任务占用智能体。 */
    record Maintenance(
            AtomicBoolean abort,
            long lastTurn,
            AtomicBoolean wakeRequested
    ) implements Phase {
        /**
         * 处理Maintenance。
         * 流程：应用领域规则 → 更新领域对象 → 返回结果。
         * @param lastTurn last Turn参数。
         * @return 方法执行结果。
         */
        public Maintenance(long lastTurn) {
            this(new AtomicBoolean(false), lastTurn, new AtomicBoolean(false));
        }
    }

    /** 运行阶段：驱动器正在推进轮次和步骤。 */
    record Running(
            AtomicBoolean abort,
            long turn,
            long step,
            AtomicBoolean wakeRequested
    ) implements Phase {
        /**
         * 运行ning。
         */
        public Running(long lastTurn) {
            this(new AtomicBoolean(false), lastTurn, 0, new AtomicBoolean(false));
        }
    }
}

