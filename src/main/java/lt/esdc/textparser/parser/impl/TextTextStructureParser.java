package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTextStructureParser extends TextStructureParser {
  private static final Pattern TEXT_PATTERN = Pattern.compile(".*", Pattern.DOTALL);

  @Override
  protected Matcher getMatcher(String text) {
    return TEXT_PATTERN.matcher(text);
  }
}
