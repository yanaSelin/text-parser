package lt.esdc.textparser.parser.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.interpreter.ExpressionInterpreter;

/**
 * Parser implementation for mathematical expressions found in text.
 * This parser identifies expressions based on a regex pattern and uses
 * an interpreter to evaluate the expressions.
 */
class ExpressionTextStructureParser extends AbstractTextComponentParser {
  private static final Pattern EXPRESSION_PATTERN = Pattern
        .compile("^([-+(~]+\\d+[\\d&<>|(/)+~\\-*^]*|\\d+[&<>|(/)+~\\-*^][\\d&<>|(/)+~\\-*^]*)");

  private final ExpressionInterpreter interpreter;

  /**
   * Constructs an expression parser with the specified interpreter.
   *
   * @param interpreter the expression interpreter used to evaluate expressions
   */
  ExpressionTextStructureParser(ExpressionInterpreter interpreter) {
    this.interpreter = interpreter;
  }

  /**
   * Creates a matcher for identifying expressions in text.
   *
   * @param text the text to search for expressions
   * @return a matcher for expression patterns
   */
  @Override
  protected Matcher getMatcher(String text) {
    return EXPRESSION_PATTERN.matcher(text);
  }

  /**
   * Parses an identified expression chunk by interpreting it and passing
   * the result to the next parser in the chain.
   *
   * @param chunk the ParseObject containing the expression to be interpreted
   * @return the parsed TextComponent result
   */
  @Override
  protected TextComponent parseChunk(ParseObject chunk) {
    String expressionText = chunk.getText();
    String result = interpreter.interpret(expressionText);
    return parseNext(new ParseObject(result));
  }
}
