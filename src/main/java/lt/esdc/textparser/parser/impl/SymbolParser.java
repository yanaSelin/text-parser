package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.Symbol;

public class SymbolParser extends AbstractTextComponentParser {
  private static final Pattern SYMBOL_PATTERN = Pattern.compile("^.", Pattern.DOTALL);

  @Override
  protected Matcher getMatcher(String text) {
    return SYMBOL_PATTERN.matcher(text);
  }

  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    return new Symbol(chunk.getText().charAt(0));
  }
}
