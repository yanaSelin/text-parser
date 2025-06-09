package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

/**
 * Parser implementation for words within text.
 * This parser identifies words consisting of Latin or Cyrillic characters as well as
 * numeric values with potential hyphens, and creates the corresponding text structure components.
 */
class WordTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern WORD_PATTERN = Pattern.compile("^[а-яА-ЯёЁa-zA-Z]+|^(-?[0-9]+)");

  /**
   * Creates a matcher for identifying words in text.
   * Words are identified as sequences of Latin or Cyrillic letters,
   * and may contain hyphens and numbers.
   *
   * @param text the text to search for words
   * @return a matcher for word patterns
   */
  @Override
  protected Matcher getMatcher(String text) {
    return WORD_PATTERN.matcher(text);
  }

  /**
   * Creates a new text structure with word component type.
   *
   * @return a TextStructure representing a word
   */
  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.WORD);
  }
}
