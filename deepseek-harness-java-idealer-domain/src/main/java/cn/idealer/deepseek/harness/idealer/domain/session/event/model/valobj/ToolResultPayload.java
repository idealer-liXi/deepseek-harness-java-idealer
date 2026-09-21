package cn.idealer.deepseek.harness.idealer.domain.session.event.model.valobj;

import cn.idealer.deepseek.harness.idealer.types.model.entity.Message;

import java.util.Map;

/**
 * ToolResultPayload 表示ToolResultPayload结果。
 */
public record ToolResultPayload(
        long turn,
        long step,
        Message message,
        Map<String, Object> error,
        Map<String, Object> meta
) {}
