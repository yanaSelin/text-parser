package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponent;

public class ExpressionTextStructureParser extends AbstractTextComponentParser {
  private static final Pattern EXPRESSION_PATTERN = Pattern
        .compile("^([-+(~]+\\d+[\\d&<>|(/)+~\\-*^]*|\\d+[&<>|(/)+~\\-*^][\\d&<>|(/)+~\\-*^]*)");

  @Override
  protected Matcher getMatcher(String text) {
    return EXPRESSION_PATTERN.matcher(text);
  }

  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    return parseNext(new ParseObject("calculated"));
  }
}
