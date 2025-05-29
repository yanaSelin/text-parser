package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import lt.esdc.textparser.composite.TextComponent;

public class MockParser extends TextComponentParser {
  private final int maxLength;

  public MockParser() {
    this(0);
  }

  public MockParser(int maxLength) {
    this.maxLength = maxLength;
  }

  @Override
  public TextComponent parse(ParseObject text) {
    int textLength = text.getText().length();
    int limit = maxLength == 0 ? textLength : Math.min(text.getPointer() + maxLength, textLength);
    TextComponent component = new MockTextComponent(text.getText().substring(text.getPointer(), limit));
    text.incrementPointer(limit - text.getPointer());
    return component;
  }

  @Override
  protected Matcher getMatcher(String text) {
    return null;
  }

  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    return null;
  }

  @Override
  public TextComponent parse(String text) {
    return parse(new ParseObject(text));
  }
}
