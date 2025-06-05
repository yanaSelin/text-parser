package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

public class WordTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern WORD_PATTERN = Pattern.compile("^[а-яА-ЯёЁa-zA-Z(-?0-9)]+");

  @Override
  protected Matcher getMatcher(String text) {
    return WORD_PATTERN.matcher(text);
  }

  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.WORD);
  }
}
