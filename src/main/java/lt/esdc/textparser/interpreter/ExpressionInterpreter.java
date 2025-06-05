package lt.esdc.textparser.interpreter;

/**
 * Interface for interpreting arithmetic and bitwise expressions.
 */
public interface ExpressionInterpreter {

  /**
   * Interprets an arithmetic or bitwise expression and returns the result.
   *
   * @param expression the expression to interpret
   * @return the result of the expression evaluation
   */
  String interpret(String expression);
}
