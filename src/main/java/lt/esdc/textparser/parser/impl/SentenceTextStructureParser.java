package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceTextStructureParser extends TextStructureParser {
  private static final Pattern SENTENCE_PATTERN =
        Pattern.compile("^[A-ZА-Я][\\s\\S]*?(?:\\.\\.\\.|!|\\?|\\.)(?=\\s*(?:[A-ZА-Я]|$))");

  @Override
  protected Matcher getMatcher(String text) {
    return SENTENCE_PATTERN.matcher(text);
  }
}
