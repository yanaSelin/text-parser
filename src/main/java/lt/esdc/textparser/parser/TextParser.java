package lt.esdc.textparser.parser;

import lt.esdc.textparser.composite.TextComponent;

public interface TextParser {
  TextComponent parse(String text);
}
