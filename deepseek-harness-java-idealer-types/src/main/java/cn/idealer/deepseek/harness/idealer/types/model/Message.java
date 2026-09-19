package cn.idealer.deepseek.harness.idealer.types.model;

import java.util.List;
import java.util.UUID;

public record Message(String id, MessageRole role, List<ContentBlock> content) {
    public Message {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id is required");
        if (role == null) throw new IllegalArgumentException("role is required");
        content = List.copyOf(content == null ? List.of() : content);
    }

    public static Message user(String text) {
        return new Message(UUID.randomUUID().toString(), MessageRole.USER, List.of(new TextBlock(text)));
    }
}

