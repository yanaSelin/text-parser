package lt.esdc.textparser.parser;

import lt.esdc.textparser.composite.TextComponent;

/**
 * Interface defining the behavior for text parsing operations.
 * Implementations of this interface are responsible for converting
 * raw text strings into structured TextComponent hierarchies.
 */
public interface TextParser {
  /**
   * Parses the provided text string into a structured TextComponent hierarchy.
   *
   * @param text the raw text string to parse
   * @return a TextComponent representing the parsed structure
   */
  TextComponent parse(String text);
}
