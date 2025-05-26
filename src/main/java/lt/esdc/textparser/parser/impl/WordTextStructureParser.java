package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordTextStructureParser extends TextStructureParser {
  private static final Pattern WORD_PATTERN = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+");

  @Override
  protected Matcher getMatcher(String text) {
    return WORD_PATTERN.matcher(text);
  }
}
