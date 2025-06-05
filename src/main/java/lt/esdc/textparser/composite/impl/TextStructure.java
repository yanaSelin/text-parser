package lt.esdc.textparser.composite.impl;

import java.util.ArrayList;
import java.util.List;
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

  //  @Override
//  public List<TextComponent> getComponents(TextComponentType type) {
//    return type == this.type ? List.of(this) : children.stream()
//          .flatMap((child) -> child.getComponents(type).stream())
//          .toList();
//  }

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
}
