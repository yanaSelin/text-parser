package lt.esdc.textparser.composite.impl;

import java.util.List;
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
}
