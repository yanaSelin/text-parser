package lt.esdc.textparser.composite.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;

/**
 * Implementation of the TextComponent interface representing composite text structures
 * such as text, paragraphs, sentences, and words.
 * This class implements the Composite part of the Composite design pattern,
 * containing child components and managing operations on them.
 */
public class TextStructure implements TextComponent {
  private final List<TextComponent> children = new ArrayList<>();

  private final TextComponentType type;

  /**
   * Constructs a new TextStructure with the specified type.
   *
   * @param type the type of this text structure
   */
  public TextStructure(TextComponentType type) {
    this.type = type;
  }

  /**
   * Adds a child component to this text structure.
   *
   * @param child the child component to add
   */
  public void addChild(TextComponent child) {
    children.add(child);
  }

  /**
   * Gets a defensive copy of all child components.
   *
   * @return a new list containing all child components
   */
  public List<TextComponent> getChildren() {
    return new ArrayList<>(children);
  }

  /**
   * Returns string representation of this text structure by concatenating
   * string representations of all child components.
   *
   * @return the string representation
   */
  @Override
  public String toString() {
    return children.stream()
          .map(TextComponent::toString)
          .collect(Collectors.joining(""));
  }

  /**
   * Calculates the total length of this text structure by summing
   * the lengths of all child components.
   *
   * @return the total length
   */
  @Override
  public int length() {
    return children.stream()
          .mapToInt(TextComponent::length)
          .sum();
  }

  /**
   * Gets the type of this text structure.
   *
   * @return the component type
   */
  @Override
  public TextComponentType getType() {
    return type;
  }

  /**
   * Recursively retrieves all components of the specified type from this structure
   * and its child components.
   *
   * @param type the type of components to retrieve
   * @return a list of all matching components
   */
  @Override
  public List<TextComponent> getComponents(TextComponentType type) {
    return children.stream()
          .flatMap(child -> child.getType().equals(type)
                ? Stream.of(child)
                : child.getComponents(type).stream())
          .toList();
  }

  /**
   * Removes the specified component from this structure or its children.
   * If the component is a direct child, it is removed directly.
   * Otherwise, the removal request is propagated to all children.
   *
   * @param component the component to remove
   * @return true if the component was found and removed, false otherwise
   */
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

  /**
   * Checks if this text structure is equal to another object.
   * Two text structures are considered equal if they have the same type
   * and the same children.
   *
   * @param o the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TextStructure that = (TextStructure) o;
    return Objects.equals(children, that.children) && type == that.type;
  }

  /**
   * Generates a hash code for this text structure.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(children, type);
  }
}
