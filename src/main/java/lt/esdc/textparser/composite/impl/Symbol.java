package lt.esdc.textparser.composite.impl;

import java.util.List;
import java.util.Objects;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;

/**
 * Implementation of the TextComponent interface representing a single character symbol.
 * This class implements the Leaf part of the Composite design pattern,
 * representing the most basic, indivisible text component.
 */
public class Symbol implements TextComponent {
  private final TextComponentType type = TextComponentType.SYMBOL;
  private final char character;

  /**
   * Constructs a new Symbol with the specified character.
   *
   * @param character the character this symbol represents
   */
  public Symbol(char character) {
    this.character = character;
  }

  /**
   * Gets the character this symbol represents.
   *
   * @return the character
   */
  public char getCharacter() {
    return character;
  }

  /**
   * Returns string representation of this symbol (the character itself).
   *
   * @return string representation of the character
   */
  @Override
  public String toString() {
    return String.valueOf(character);
  }

  /**
   * Gets the type of this component, which is always SYMBOL.
   *
   * @return the component type (SYMBOL)
   */
  @Override
  public TextComponentType getType() {
    return type;
  }

  /**
   * Gets the length of this symbol, which is always 1.
   *
   * @return the length (always 1)
   */
  @Override
  public int length() {
    return 1;
  }

  /**
   * Returns an empty list since symbols cannot contain other components.
   * As this is a leaf node in the composite pattern, it has no children.
   *
   * @param type the type of components to retrieve
   * @return an empty list
   */
  @Override
  public List<TextComponent> getComponents(TextComponentType type) {
    return List.of();
  }

  /**
   * Does nothing and returns false, as symbols cannot contain other components.
   * As this is a leaf node in the composite pattern, it cannot remove children.
   *
   * @param component the component to remove
   * @return false (always)
   */
  @Override
  public boolean removeComponent(TextComponent component) {
    return false;
  }

  /**
   * Checks if this symbol is equal to another object.
   * Two symbols are considered equal if they represent the same character.
   *
   * @param o the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Symbol symbol = (Symbol) o;
    return character == symbol.character && type == symbol.type;
  }

  /**
   * Generates a hash code for this symbol.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(type, character);
  }
}
