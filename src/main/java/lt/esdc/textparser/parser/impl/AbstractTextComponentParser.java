package lt.esdc.textparser.parser.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.Chainable;
import lt.esdc.textparser.parser.TextParser;

/**
 * Abstract base class for text component parsers
 * that implements both TextParser and Chainable interfaces.
 * This class forms the foundation of the parser chain of responsibility pattern,
 * where each parser attempts to parse a portion of text
 * and delegates to the next parser in the chain if unable to do so.
 * Each concrete implementation should define its specific parsing logic
 * by implementing the abstract methods.
 */
abstract class AbstractTextComponentParser
      implements TextParser, Chainable<AbstractTextComponentParser> {
  protected Optional<AbstractTextComponentParser> next = Optional.empty();

  /**
   * Creates a matcher for the text based on the parser's pattern.
   *
   * @param text the text to match against
   * @return a Matcher configured for the specific parser implementation
   */
  protected abstract Matcher getMatcher(String text);

  /**
   * Processes a matched chunk of text and converts it to a TextComponent.
   *
   * @param chunk the ParseObject containing the text chunk to parse
   * @return a TextComponent representing the parsed chunk
   */
  protected abstract TextComponent parseChunk(ParseObject chunk);

  @Override
  public void setNext(AbstractTextComponentParser parser) {
    next = Optional.of(parser);
  }

  /**
   * Delegates parsing to the next parser in the chain.
   *
   * @param text the string to be parsed
   * @return the TextComponent produced by the next parser
   * @throws IllegalStateException if no suitable parser is found in the chain
   */
  protected TextComponent parseNext(String text) {
    if (next.isPresent()) {
      return next.get().parse(text);
    }

    throw new IllegalStateException("No parser found for text: " + text);
  }

  /**
   * Delegates parsing to the next parser in the chain using a ParseObject.
   *
   * @param text the ParseObject to be parsed
   * @return the TextComponent produced by the next parser
   * @throws IllegalStateException if no suitable parser is found in the chain
   */
  protected TextComponent parseNext(ParseObject text) {
    if (next.isPresent()) {
      return next.get().parse(text);
    }

    throw new IllegalStateException("No parser found for text: " + text);
  }

  /**
   * Entry point for parsing text strings. Creates a new ParseObject and delegates to the
   * overloaded parse method.
   *
   * @param text the string to parse
   * @return a TextComponent representing the parsed structure
   */
  public TextComponent parse(String text) {
    return parse(new ParseObject(text));
  }

  /**
   * Core parsing method that attempts to match and process text from the current position.
   * If a match is found, the text is processed; otherwise, parsing is delegated to the next
   * parser in the chain.
   *
   * @param text the ParseObject containing the text and current parsing position
   * @return a TextComponent representing the parsed structure
   */
  protected TextComponent parse(ParseObject text) {
    Matcher matcher = getMatcher(text.getText().substring(text.getPointer()));

    if (matcher.find()) {
      String chunk = matcher.group();
      text.incrementPointer(chunk.length());
      return parseChunk(new ParseObject(chunk));
    }

    return parseNext(text);
  }
}
