package cn.idealer.deepseek.harness.idealer.domain.agent.service.run;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.entity.AgentOptions;
import cn.idealer.deepseek.harness.idealer.domain.agent.service.Agent;
import cn.idealer.deepseek.harness.idealer.domain.tool.service.ToolCallExecutor;
import cn.idealer.deepseek.harness.idealer.types.model.valobj.ToolCallBlock;

import java.util.concurrent.CompletableFuture;

public interface AgentRun extends Agent {

    /**
     * 热更新下一次模型调用使用的运行参数。
     *
     * @param options 领域配置。
     */
    void updateOptions(AgentOptions options);

    /**
     * 绑定流式文本增量接收器。
     */
    void setStreamDeltaSink(java.util.function.Consumer<String> sink);

    /**
     * 绑定流式推理增量接收器。
     */
    void setStreamReasoningSink(java.util.function.Consumer<String> sink);

    /**
     * 绑定工具调用事件接收器。
     */
    void setStreamToolCallSink(java.util.function.Consumer<ToolCallBlock> sink);

    /**
     * 绑定工具声明周期事件接收器
     * @param sink
     */
    void setStreamToolEventSink(java.util.function.Consumer<
            ToolCallExecutor.ToolLifecycleEvent> sink);

    /**
     * 绑定完成回调方法
     * @param sink
     */
    void setStreamFinishSink(Runnable sink);

    /**
     * 释放当前运行占用的资源。
     */
    CompletableFuture<Void> dispose();
}

