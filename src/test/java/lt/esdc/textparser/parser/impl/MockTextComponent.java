package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;

public record MockTextComponent(String content) implements TextComponent {
    @Override
    public int length() {
        return content.length();
    }

    @Override
    public String toString() {
        return content;
    }
}
