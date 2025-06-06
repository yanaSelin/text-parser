package lt.esdc.textparser.interpreter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lt.esdc.textparser.util.StringUtil;

/**
 * Converts mathematical expressions from infix notation (standard form) to postfix notation
 * (Reverse Polish Notation) using the shunting yard algorithm.
 * Infix notation is the common way of writing expressions
 * where operators appear between operands (e.g. 3 + 4).
 * Postfix notation places operators after their operands (e.g. 3 4 +),
 * which is easier for computers to evaluate
 * without requiring parentheses to denote precedence.
 */
public class InfixToPostfixConverter {
  /**
   * Converts infix notation to postfix (Reverse Polish Notation) using the shunting yard algorithm.
   *
   * @param tokens List of tokens (numbers and operators) in infix notation
   * @return List of tokens in postfix notation
   * @throws IllegalArgumentException if there are mismatched parentheses
   */
  List<String> convert(List<String> tokens) {
    List<String> output = new ArrayList<String>();
    Stack<String> operators = new Stack<String>();

    for (String token : tokens) {
      if (StringUtil.isNumeric(token)) {
        // If token is a number, add it to output
        output.add(token);
      } else if (TokenOperatorType.LEFT_PARENTHESIS.equals(token)) {
        // If token is an opening parenthesis, push it to the stack
        operators.push(token);
      } else if (TokenOperatorType.RIGHT_PARENTHESIS.equals(token)) {
        // If token is a closing parenthesis, pop operators until opening parenthesis
        while (!operators.isEmpty()
              && !TokenOperatorType.LEFT_PARENTHESIS.equals(operators.peek())) {
          output.add(operators.pop());
        }
        if (!operators.isEmpty()
              && TokenOperatorType.LEFT_PARENTHESIS.equals(operators.peek())) {
          operators.pop(); // Discard the opening parenthesis
        }
      } else {
        // If token is an operator, handle precedence
        while (!operators.isEmpty()
              && !StringUtil.isNumeric(operators.peek())
              && getPrecedence(operators.peek()) >= getPrecedence(token)) {
          output.add(operators.pop());
        }
        operators.push(token);
      }
    }

    // Pop any remaining operators
    while (!operators.isEmpty()) {
      if (OperatorSymbolType.LEFT_PARENTHESIS.equals(operators.peek())) {
        throw new IllegalArgumentException("Mismatched parentheses");
      }
      output.add(operators.pop());
    }

    return output;
  }

  /**
   * Gets the precedence of an operator to determine evaluation order.
   * Higher precedence operators are evaluated before lower precedence ones.
   *
   * @param operator The operator symbol to check
   * @return The precedence value of the operator, or -1 if not recognized
   */
  private int getPrecedence(String operator) {
    TokenOperatorType operationType = TokenOperatorType.valueOf(operator);
    return operationType.getPrecedence();
  }
}