package cn.idealer.deepseek.harness.idealer.infrastructure.tool;

import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolCallExecutor;
import cn.idealer.deepseek.harness.idealer.domain.tool.port.ToolRegistry;
import cn.idealer.deepseek.harness.idealer.types.model.ToolCallBlock;
import cn.idealer.deepseek.harness.idealer.types.model.ToolResultBlock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public final class DefaultToolCallExecutor implements ToolCallExecutor {
    private final ToolRegistry registry;

    public DefaultToolCallExecutor(ToolRegistry registry) { this.registry = registry; }

    @Override
    public CompletionStage<List<ToolResultBlock>> execute(long turn, long step, List<ToolCallBlock> calls) {
        // TODO：lookup → execute → 记录 tool/call 和 tool/result → 保持输入顺序。
        return CompletableFuture.failedFuture(new UnsupportedOperationException("TODO: implement tool execution"));
    }
}

