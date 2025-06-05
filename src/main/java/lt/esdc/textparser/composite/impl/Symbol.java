package lt.esdc.textparser.composite.impl;

import java.util.List;
import java.util.Objects;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;

public class Symbol implements TextComponent {
  private final TextComponentType type = TextComponentType.SYMBOL;
  private final char character;

  public Symbol(char character) {
    this.character = character;
  }

  public char getCharacter() {
    return character;
  }

  @Override
  public String toString() {
    return String.valueOf(character);
  }

  @Override
  public TextComponentType getType() {
    return type;
  }

  @Override
  public int length() {
    return 1;
  }

  @Override
  public List<TextComponent> getComponents(TextComponentType type) {
    return List.of();
  }

  @Override
  public boolean removeComponent(TextComponent component) {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Symbol symbol = (Symbol) o;
    return character == symbol.character && type == symbol.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, character);
  }
}
