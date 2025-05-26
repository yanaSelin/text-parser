package lt.esdc.textparser.parser;

import lt.esdc.textparser.composite.TextComponent;

/**
 * The Parser interface defines the contract for parsing text into TextComponent objects.
 * It allows for chaining parsers together to create a flexible parsing structure.
 */
public interface TextComponentParser extends Parser {
  /**
   * Sets the next parser in the chain.
   *
   * @param parser the next parser to set
   */
  void setNext(TextComponentParser parser);

  /**
   * Links multiple parsers together, creating a chain of responsibility.
   *
   * @param first    the first parser in the chain
   * @param parsers  additional parsers to link
   * @return the first parser in the chain
   */
  static TextComponentParser link(TextComponentParser first, TextComponentParser... parsers) {
    TextComponentParser head = first;

    for (TextComponentParser parser : parsers) {
      head.setNext(parser);
      head = parser;
    }

    return first;
  }
}
