package lt.esdc.textparser.parser.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.Chainable;
import lt.esdc.textparser.parser.TextParser;

abstract class AbstractTextComponentParser implements TextParser, Chainable<AbstractTextComponentParser> {
  protected Optional<AbstractTextComponentParser> next = Optional.empty();

  protected abstract Matcher getMatcher(String text);

  protected abstract TextComponent parseChunk(ParseObject chunk);

  @Override
  public void setNext(AbstractTextComponentParser parser) {
    next = Optional.of(parser);
  }

  protected TextComponent parseNext(String text) {
    if (next.isPresent()) {
      return next.get().parse(text);
    }

    throw new IllegalStateException("No parser found for text: " + text);
  }

  protected TextComponent parseNext(ParseObject text) {
    if (next.isPresent()) {
      return next.get().parse(text);
    }

    throw new IllegalStateException("No parser found for text: " + text);
  }

  public TextComponent parse(String text) {
    return parse(new ParseObject(text));
  }

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
