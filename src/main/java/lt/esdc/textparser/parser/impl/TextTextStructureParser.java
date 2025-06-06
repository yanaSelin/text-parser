package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

/**
 * Parser implementation for entire text documents.
 * This parser treats the entire input as a single text component and creates
 * the top-level text structure component.
 */
public class TextTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern TEXT_PATTERN = Pattern.compile(".*", Pattern.DOTALL);

  /**
   * Creates a matcher that matches the entire text.
   * The pattern is designed to match all characters including newlines.
   *
   * @param text the text to match
   * @return a matcher that matches the entire input text
   */
  @Override
  protected Matcher getMatcher(String text) {
    return TEXT_PATTERN.matcher(text);
  }

  /**
   * Creates a new text structure with the top-level TEXT component type.
   *
   * @return a TextStructure representing the entire text document
   */
  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.TEXT);
  }
}
