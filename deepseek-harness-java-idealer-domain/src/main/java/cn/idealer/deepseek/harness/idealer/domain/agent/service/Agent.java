package cn.idealer.deepseek.harness.idealer.domain.agent.service;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.valobj.AgentStatus;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.valobj.InboxTarget;
import cn.idealer.deepseek.harness.idealer.domain.agent.model.entity.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.session.event.model.entity.SessionLog;
import cn.idealer.deepseek.harness.idealer.types.model.entity.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public interface Agent {
    /**
     * @return 处理ID
     */
    String id();

    /**
     * @return 处理智能体选项
     */
    AgentOptions options();

    /**
     * @return 处理智能体状态
     */
    AgentStatus status();

    /**
     * 处理Inbox
     *
     * @return 领域处理结果
     */
    Inbox inbox();

    /**
     * 处理会话
     *
     * @return 领域处理结果
     */
    SessionLog session();

    /**
     * 将输入路由到Inbox边界，并决定是否唤醒
     *
     * @param message 发过来的消息
     * @param target 消息目标类型
     * @param wakeup  是否唤醒
     */
    void send(Message message, InboxTarget target, boolean wakeup);

    CompletableFuture<Void> whenIdle();

    /**
     * 取消执行
     */
    void cancel();

    /**
     * 追加下一轮跟进消息并唤醒驱动。
     * <p>流程：校验入参 → 执行领域动作 → 返回处理结果。</p>
     * @param message 消息。
     */
    default void followup(Message message) {
        send(message, InboxTarget.NEXT_TURN, true);
    }

    /**
     * 在空闲阶段执行一次维护任务并保持任务完成状态。
     * <p>流程：校验入参 → 执行领域动作 → 返回处理结果。</p>
     * @param task task。
     * @return 领域处理结果。
     */
    <T> CompletableFuture<T> runMaintenance(Function<AtomicBoolean, CompletableFuture<T>> task);
}

