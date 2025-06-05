package lt.esdc.textparser.composite.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;

public class TextStructure implements TextComponent {
  private final List<TextComponent> children = new ArrayList<>();

  private final TextComponentType type;

  public TextStructure(TextComponentType type) {
    this.type = type;
  }

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

  @Override
  public TextComponentType getType() {
    return type;
  }

  @Override
  public List<TextComponent> getComponents(TextComponentType type) {
    return children.stream()
          .flatMap(child -> child.getType().equals(type)
                ? Stream.of(child)
                : child.getComponents(type).stream())
          .toList();
  }

  @Override
  public boolean removeComponent(TextComponent component) {
    if (children.remove(component)) {
      return true;
    }

    for (TextComponent child : children) {
      if (child.removeComponent(component)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TextStructure that = (TextStructure) o;
    return Objects.equals(children, that.children) && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(children, type);
  }
}
