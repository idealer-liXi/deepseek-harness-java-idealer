package cn.idealer.deepseek.harness.idealer.domain.tool.port;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ToolCallExecutor {
    CompletionStage<List<ToolResultBlock>> execute(long turn, long step, List<ToolCallBlock> calls);
}

