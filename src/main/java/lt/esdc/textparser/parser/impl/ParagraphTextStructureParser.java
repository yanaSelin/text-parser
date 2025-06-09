package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

/**
 * Parser implementation for paragraphs within text.
 * This parser identifies paragraphs based on tab indentation and creates
 * the corresponding text structure components.
 */
class ParagraphTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern PARAGRAPH_PATTERN = Pattern.compile("(^\t[\\s\\S]*?)(?=\n\t|$)");

  /**
   * Creates a matcher for identifying paragraphs in text.
   * Paragraphs are identified by tab character at the beginning and continue until
   * either another tab character or the end of input.
   *
   * @param text the text to search for paragraphs
   * @return a matcher for paragraph patterns
   */
  @Override
  protected Matcher getMatcher(String text) {
    return PARAGRAPH_PATTERN.matcher(text);
  }

  /**
   * Creates a new text structure with paragraph component type.
   *
   * @return a TextStructure representing a paragraph
   */
  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.PARAGRAPH);
  }
}
