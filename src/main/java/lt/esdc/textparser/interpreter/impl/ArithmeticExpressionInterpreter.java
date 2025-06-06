package lt.esdc.textparser.interpreter.impl;

import java.util.List;
import lt.esdc.textparser.interpreter.ExpressionInterpreter;

/**
 * Implementation of ExpressionInterpreter that can interpret arithmetic and bitwise expressions.
 * Supports:
 * 1. Arithmetic operations (+, -, *, /)
 * 2. Unary operations (++, --, ~)
 * 3. Bitwise operations (|, ^, &, >>, <<)
 * 4. Parentheses for grouping expressions
 */
public class ArithmeticExpressionInterpreter implements ExpressionInterpreter {

  private final Tokenizer tokenizer = new Tokenizer();

  private final InfixToPostfixConverter infixToPostfixConverter = new InfixToPostfixConverter();

  private final PostfixEvaluator evaluator = new PostfixEvaluator();

  @Override
  public String interpret(String expression) {
    if (expression == null || expression.isEmpty()) {
      throw new IllegalArgumentException("Expression cannot be null or empty");
    }

    List<String> tokens = tokenizer.tokenize(expression);
    List<String> postfix = infixToPostfixConverter.convert(tokens);
    return evaluator.evaluate(postfix);
  }
}
