package cn.idealer.deepseek.harness.idealer.domain.tool.port;

import cn.idealer.deepseek.harness.idealer.types.model.ToolCallBlock;
import cn.idealer.deepseek.harness.idealer.types.model.ToolResultBlock;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ToolCallExecutor {
    CompletionStage<List<ToolResultBlock>> execute(long turn, long step, List<ToolCallBlock> calls);
}

