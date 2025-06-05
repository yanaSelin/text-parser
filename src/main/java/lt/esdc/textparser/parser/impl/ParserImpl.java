package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.interpreter.impl.ArithmeticExpressionInterpreter;
import lt.esdc.textparser.parser.Chainable;
import lt.esdc.textparser.parser.TextParser;

public class ParserImpl implements TextParser {
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
