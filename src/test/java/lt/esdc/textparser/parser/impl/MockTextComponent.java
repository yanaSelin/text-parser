package lt.esdc.textparser.parser.impl;

import java.util.List;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;

public record MockTextComponent(String content) implements TextComponent {
    @Override
    public TextComponentType getType() {
        return null;
    }

    @Override
    public int length() {
        return content.length();
    }

    @Override
    public List<TextComponent> getComponents(TextComponentType type) {
        return List.of(this);
    }

    @Override
    public boolean removeComponent(TextComponent component) {
        return false;
    }

    @Override
    public String toString() {
        return content;
    }
}
