package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.Symbol;

/**
 * Parser responsible for processing individual character symbols.
 * This parser represents the lowest level in the text parsing hierarchy, converting individual
 * characters into Symbol objects. It matches any single character using a regular expression
 * pattern that accepts any character (including newlines with DOTALL flag).
 * As the terminal parser in the chain of responsibility, SymbolParser typically doesn't delegate
 * to another parser.
 */
class SymbolParser extends AbstractTextComponentParser {
  private static final Pattern SYMBOL_PATTERN = Pattern.compile("^.", Pattern.DOTALL);

  /**
   * Creates a matcher that matches any single character at the beginning of the text.
   *
   * @param text the text to match against
   * @return a Matcher configured to match any single character
   */
  @Override
  protected Matcher getMatcher(String text) {
    return SYMBOL_PATTERN.matcher(text);
  }

  /**
   * Converts a matched character into a Symbol object.
   *
   * @param chunk the ParseObject containing a single character
   * @return a Symbol object representing the character
   */
  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    return new Symbol(chunk.getText().charAt(0));
  }
}
