package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.Chainable;
import lt.esdc.textparser.parser.Parser;

public class ParserImpl implements Parser {
  @Override
  public TextComponent parse(String text) {
    Parser parser = Chainable.link(
          new TextTextStructureParser(),
          new ParagraphTextStructureParser(),
          new SentenceTextStructureParser(),
          new ExpressionTextStructureParser(),
          new WordTextStructureParser(),
          new SymbolParser()
    );

    return parser.parse(text);
  }
}
