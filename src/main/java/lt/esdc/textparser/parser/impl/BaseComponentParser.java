package lt.esdc.textparser.parser.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.TextComponentParser;

public abstract class BaseComponentParser implements TextComponentParser {
  protected Optional<TextComponentParser> next = Optional.empty();

  protected abstract Matcher getMatcher(String text);

  protected abstract TextComponent buildComponent(String text);

  @Override
  public void setNext(TextComponentParser parser) {
    next = Optional.of(parser);
  }

  protected TextComponent parseNext(String text) {
    if (next.isPresent()) {
      return next.get().parse(text);
    }

    throw new IllegalStateException("No parser found for text: " + text);
  }

  public TextComponent parse(String text) {
    Matcher matcher = getMatcher(text);

    if (matcher.find()) {
      return buildComponent(matcher.group());
    }

    return parseNext(text);
  }

//  public TextComponent parse(String text) {
//    return parse(text, 0);
//  }
}
