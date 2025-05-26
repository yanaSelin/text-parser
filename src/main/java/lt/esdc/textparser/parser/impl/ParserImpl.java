package lt.esdc.textparser.parser.impl;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.Parser;
import lt.esdc.textparser.parser.TextComponentParser;

public class ParserImpl implements Parser {
  @Override
  public TextComponent parse(String text) {
    TextComponentParser parser = TextComponentParser.link(
          new TextTextStructureParser(),
          new ParagraphTextStructureParser(),
          new SentenceTextStructureParser(),
          new WordTextStructureParser(),
          new SymbolParser()
    );

    return parser.parse(text);
  }
}
