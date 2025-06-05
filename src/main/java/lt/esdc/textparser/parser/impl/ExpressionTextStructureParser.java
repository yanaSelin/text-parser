package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.interpreter.ExpressionInterpreter;

public class ExpressionTextStructureParser extends AbstractTextComponentParser {
  private static final Pattern EXPRESSION_PATTERN = Pattern
        .compile("^([-+(~]+\\d+[\\d&<>|(/)+~\\-*^]*|\\d+[&<>|(/)+~\\-*^][\\d&<>|(/)+~\\-*^]*)");

  private final ExpressionInterpreter interpreter;

  public ExpressionTextStructureParser(ExpressionInterpreter interpreter) {
    this.interpreter = interpreter;
  }

  @Override
  protected Matcher getMatcher(String text) {
    return EXPRESSION_PATTERN.matcher(text);
  }

  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    String expressionText = chunk.getText();
    String result = interpreter.interpret(expressionText);
    return parseNext(new ParseObject(result));
  }
}
