package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.interpreter.impl.ArithmeticExpressionInterpreter;
import lt.esdc.textparser.parser.Chainable;
import lt.esdc.textparser.parser.TextParser;

/**
 * Main implementation of the TextParser interface that orchestrates the parsing process.
 * This class sets up the complete parser chain using the Chain of Responsibility pattern,
 * arranging the parsers in the correct order of precedence
 * from highest to lowest text structure level:
 * Text → Paragraphs → Sentences → Expressions → Words → Symbols.
 * Each parser in the chain handles a specific level of text structure and delegates to the next
 * parser when appropriate.
 */
public class ParserImpl implements TextParser {

  /**
   * Parses the provided text by configuring and applying a chain of specialized parsers.
   * The parsing process follows a hierarchical approach, breaking down the text into
   * progressively smaller components, from text to paragraphs to sentences to words to symbols.
   * Expression parsing is handled by the ArithmeticExpressionInterpreter.
   *
   * @param text the text string to parse
   * @return a TextComponent representing the fully parsed text structure
   */
  @Override
  public TextComponent parse(String text) {
    TextParser parser = Chainable.link(
          new TextTextStructureParser(),
          new ParagraphTextStructureParser(),
          new SentenceTextStructureParser(),
          new ExpressionTextStructureParser(new ArithmeticExpressionInterpreter()),
          new WordTextStructureParser(),
          new SymbolParser()
    );

    return parser.parse(text);
  }
}
