package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

public class ParagraphTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("(^\t[\\s\\S]*?)(?=\n\t|$)");

  @Override
  protected Matcher getMatcher(String text) {
    return PARAGRAPH_PATTERN.matcher(text);
  }

  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.PARAGRAPH);
  }
}
