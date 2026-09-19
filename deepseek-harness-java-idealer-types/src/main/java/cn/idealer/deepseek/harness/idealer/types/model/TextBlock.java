package cn.idealer.deepseek.harness.idealer.types.model;

public record TextBlock(String text) implements ContentBlock {
    public TextBlock {
        if (text == null) throw new IllegalArgumentException("text must not be null");
    }
}

