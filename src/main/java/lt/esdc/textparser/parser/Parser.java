package lt.esdc.textparser.parser;

import lt.esdc.textparser.composite.TextComponent;

public interface Parser {
  TextComponent parse(String text);
}
