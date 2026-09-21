package cn.idealer.deepseek.harness.idealer.cases.agent.factory;

import cn.idealer.deepseek.harness.idealer.api.dto.AgentToolCallEventResponseDTO;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.run.AgentRun;
import cn.idealer.deepseek.harness.idealer.types.model.entity.Message;

import java.util.function.Consumer;

/**
 * Agent 消息策略树中流转的可变上下文。
 *
 * <p>调用流程：HTTP/API 门面 → AgentUseCase → AgentMessageFactory → 策略树 → ReactLoopAgent；会话事件写入 SessionEventLog。</p>
 * <p>示例：调用方传入 agentId、用户消息和可选模型配置，返回助手文本与工具调用结果。</p>
 */
public class AgentMessageDynamicContext implements AutoCloseable {

    /**
     * MessageIntent 定义当前模块中的enum契约与职责。
     */
    public enum MessageIntent {
        CHAT,
        CODE_QUESTION,
        TASK_EXECUTION,
        CLARIFICATION
    }

    /**
     * 当前agent执行实例
     */
    private AgentRun agent;
    /**
     * 本次要处理的用户消息实体
     */
    private Message userMessage;
    /**
     * 消息意图分类
     */
    private MessageIntent intent;
    /**
     * 流式回答增量回调，每输出一段文本，就回调输出给用户
     */
    private Consumer<String> deltaSink;
    /**
     * 流式思考过程增量回调
     */
    private Consumer<String> reasoningSink;
    /**
     * 工具调用事件回调
     */
    private Consumer<AgentToolCallEventResponseDTO> toolCallEventSink;
    /**
     * 执行完成，信号回调
     */
    private Runnable finishSink;

    /**
     * 获取Agent。
     */
    public AgentRun getAgent() { return agent; }
    /**
     * 设置Agent。
     */
    public void setAgent(AgentRun agent) { this.agent = agent; }

    /**
     * 获取User Message。
     */
    public Message getUserMessage() { return userMessage; }
    /**
     * 设置User Message。
     */
    public void setUserMessage(Message userMessage) { this.userMessage = userMessage; }

    /**
     * 获取Intent。
     */
    public MessageIntent getIntent() { return intent; }
    /**
     * 设置Intent。
     */
    public void setIntent(MessageIntent intent) { this.intent = intent; }

    /**
     * 读取流式增量输出接收器。
     */
    public Consumer<String> getDeltaSink() { return deltaSink; }

    /**
     * 写入流式增量输出接收器，供 Agent 循环回调。
     */
    public void setDeltaSink(Consumer<String> deltaSink) { this.deltaSink = deltaSink; }

    public void setReasoningSink(Consumer<String> reasoningSink) { this.reasoningSink = reasoningSink; }

    /**
     * 读取工具调用事件接收器。
     */
    public Consumer<AgentToolCallEventResponseDTO> getToolCallEventSink() { return toolCallEventSink; }

    /**
     * 写入工具调用事件接收器，供 Agent 循环回调。
     */
    public void setToolCallEventSink(Consumer<AgentToolCallEventResponseDTO> toolCallEventSink) {
        this.toolCallEventSink = toolCallEventSink;
    }

    public void setFinishSink(Runnable finishSink) { this.finishSink = finishSink; }

    /**
     * 将上下文中的流式接收器绑定到 Agent 执行循环。
     */
    public void attachSinks(AgentRun agent) {
        if (deltaSink == null && toolCallEventSink == null) {
            return;
        }
        agent.setStreamDeltaSink(deltaSink);
        agent.setStreamReasoningSink(reasoningSink);
        agent.setStreamToolCallSink(toolCall -> {
            if (toolCallEventSink != null) {
                toolCallEventSink.accept(new AgentToolCallEventResponseDTO(
                        toolCall.id(), toolCall.name(), toolCall.arguments(), "running"
                ));
            }
        });
        agent.setStreamToolEventSink(event -> {
            if (toolCallEventSink != null) {
                toolCallEventSink.accept(new AgentToolCallEventResponseDTO(
                        event.callId(),
                        event.toolName(),
                        event.args(),
                        event.status(),
                        event.phase(),
                        event.result(),
                        event.durationMs()
                ));
            }
        });
        agent.setStreamFinishSink(finishSink);
    }

    /**
     * 解绑流式接收器并清理上下文引用。
     */
    @Override
    public void close() {
        if (agent != null) {
            agent.setStreamDeltaSink(null);
            agent.setStreamReasoningSink(null);
            agent.setStreamToolCallSink(null);
            agent.setStreamFinishSink(null);
        }
        deltaSink = null;
        reasoningSink = null;
        toolCallEventSink = null;
    }
}
