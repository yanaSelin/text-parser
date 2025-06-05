package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

public class TextTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern TEXT_PATTERN = Pattern.compile(".*", Pattern.DOTALL);

  @Override
  protected Matcher getMatcher(String text) {
    return TEXT_PATTERN.matcher(text);
  }

  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.TEXT);
  }
}
