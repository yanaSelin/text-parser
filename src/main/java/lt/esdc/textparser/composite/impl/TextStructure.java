package lt.esdc.textparser.composite.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lt.esdc.textparser.composite.TextComponent;

public class TextStructure implements TextComponent {
  protected final List<TextComponent> children = new ArrayList<>();

  public void addChild(TextComponent child) {
    children.add(child);
  }

  public List<TextComponent> getChildren() {
    return new ArrayList<>(children);
  }

  @Override
  public String toString() {
    return children.stream()
          .map(TextComponent::toString)
          .collect(Collectors.joining(""));
  }

  @Override
  public int length() {
    return children.stream()
          .mapToInt(TextComponent::length)
          .sum();
  }
}
