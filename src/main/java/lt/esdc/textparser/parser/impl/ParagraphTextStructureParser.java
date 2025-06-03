package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("(^\t[\\s\\S]*?)(?=\n\t|$)");

  @Override
  protected Matcher getMatcher(String text) {
    return PARAGRAPH_PATTERN.matcher(text);
  }
}
