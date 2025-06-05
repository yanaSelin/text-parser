package lt.esdc.textparser.interpreter.impl;

/**
 * Handler for unary operators in expressions.
 * This class is responsible for preprocessing expressions with unary operators.
 */
class UnaryOperatorHandler {
  /**
   * Preprocesses the expression to handle unary operators.
   *
   * @param expression the expression to preprocess
   * @return the processed expression with unary operators handled
   */
  public String preprocessUnaryOperators(String expression) {
    // Handle prefix unary operators
    expression = handlePrefixUnaryOperators(expression);

    // Handle postfix unary operators
    expression = handlePostfixUnaryOperators(expression);

    return expression;
  }

  /**
   * Handles prefix unary operators (++x, --x, +x, -x).
   *
   * @param expression the expression containing prefix unary operators
   * @return the processed expression
   */
  private String handlePrefixUnaryOperators(String expression) {
    // Replace ++ with +1+
    expression = expression.replaceAll("\\+\\+(?=[\\d(])", "+1+");

    // Replace -- with -1-
    expression = expression.replaceAll("--(?=[\\d(])", "-1+");

    // Replace - with 0-
    expression = expression.replaceAll("^-(?=[\\d(])", "0-");
    expression = expression.replaceAll("\\(-(?=[\\d(])", "(0-");

    // Replace + with 0+
    expression = expression.replaceAll("^\\+(?=[\\d(])", "0+");
    expression = expression.replaceAll("\\(\\+(?=[\\d(])", "(0+");

    return expression;
  }

  /**
   * Handles postfix unary operators (x++, x--).
   *
   * @param expression the expression containing postfix unary operators
   * @return the processed expression
   */
  private String handlePostfixUnaryOperators(String expression) {
    // Match digits followed by ++ and replace with the same digits
    // (postfix ++ doesn't change the value in the expression)
    expression = expression.replaceAll("([\\d+)])\\+\\+", "$1");

    // Match digits followed by -- and replace with the same digits
    // (postfix -- doesn't change the value in the expression)
    expression = expression.replaceAll("([\\d+)])--", "$1");

    return expression;
  }
}
