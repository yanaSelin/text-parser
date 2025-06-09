package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.TextStructure;

/**
 * Parser implementation for sentences within text.
 * This parser identifies sentences based on capitalization and termination punctuation marks
 * and creates the corresponding text structure components.
 */
class SentenceTextStructureParser extends AbstractTextStructureParser {
  private static final Pattern SENTENCE_PATTERN =
        Pattern.compile("^[A-ZА-Я][\\s\\S]*?(?:\\.\\.\\.|!|\\?|\\.)(?=\\s*(?:[A-ZА-Я]|$))");

  /**
   * Creates a matcher for identifying sentences in text.
   * Sentences are identified as starting with a capital letter (Latin or Cyrillic)
   * and ending with terminating punctuation (period, exclamation mark, question mark, or ellipsis)
   * followed by whitespace and another capital letter or end of input.
   *
   * @param text the text to search for sentences
   * @return a matcher for sentence patterns
   */
  @Override
  protected Matcher getMatcher(String text) {
    return SENTENCE_PATTERN.matcher(text);
  }

  /**
   * Creates a new text structure with sentence component type.
   *
   * @return a TextStructure representing a sentence
   */
  @Override
  protected TextStructure getTextStructure() {
    return new TextStructure(TextComponentType.SENTENCE);
  }
}
