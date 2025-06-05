package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

public class SentenceTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern SENTENCE_PATTERN =
        Pattern.compile("^[A-ZА-Я][\\s\\S]*?(?:\\.\\.\\.|!|\\?|\\.)(?=\\s*(?:[A-ZА-Я]|$))");

  @Override
  protected Matcher getMatcher(String text) {
    return SENTENCE_PATTERN.matcher(text);
  }

  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.SENTENCE);
  }
}
