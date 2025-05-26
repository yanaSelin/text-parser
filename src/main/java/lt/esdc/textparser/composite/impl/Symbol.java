package lt.esdc.textparser.composite.impl;

import lt.esdc.textparser.composite.TextComponent;

public class Symbol implements TextComponent {
  private final char character;

  public Symbol(char character) {
    this.character = character;
  }

  @Override
  public String toString() {
    return String.valueOf(character);
  }

  @Override
  public int length() {
    return 1;
  }
}
