package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.TextComponentParser;

public class MockParser implements TextComponentParser {
  private final int length;

  public MockParser() {
    this.length = 0;
  }

  public MockParser(int length) {
    this.length = length;
  }

  @Override
  public void setNext(TextComponentParser parser) {
  }

  @Override
  public TextComponent parse(String text) {
    int limit = length == 0 ? text.length() : Math.min(length, text.length());
    return new MockTextComponent(text.substring(0, limit));
  }

//  @Override
//  public TextComponent parse(String text, int start) {
//    return parse(text.substring(start));
//  }
}
