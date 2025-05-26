package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: implement
public class ExpressionTextStructureParser extends TextStructureParser {
  private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("");

  @Override
  protected Matcher getMatcher(String text) {
    return PARAGRAPH_PATTERN.matcher(text);
  }
}
