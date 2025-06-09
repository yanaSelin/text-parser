package lt.esdc.textparser.interpreter.impl;

import java.util.List;
import java.util.Stack;
import lt.esdc.textparser.util.StringUtil;

/**
 * Evaluates expressions in postfix notation (Reverse Polish Notation).
 *
 * <p>This class processes a list of tokens in postfix notation and computes the final result.
 * It supports arithmetic operations (+, -, *, /), bitwise operations (|, ^, &, >>, <<, ~),
 * and follows proper operator precedence rules.</p>
 */
class PostfixEvaluator {
  /**
   * Evaluates a postfix expression and returns the computed result.
   *
   * @param postfix A list of tokens in postfix notation
   * @return The result of the expression evaluation as a string
   * @throws IllegalArgumentException if the expression is invalid or contains unsupported operators
   * @throws ArithmeticException if a division by zero occurs
   */
  String evaluate(List<String> postfix) {
    Stack<Integer> operandStack = new Stack<>();

    for (String token : postfix) {
      if (StringUtil.isNumeric(token)) {
        operandStack.push(Integer.parseInt(token));
      } else {
        TokenOperatorType operator = TokenOperatorType.valueOf(token);
        if (operator.isUnary()) {
          if (operandStack.isEmpty()) {
            throw new IllegalArgumentException(
                  "Invalid expression: missing operand for operator " + operator);
          }

          int operand = operandStack.pop();
          operandStack.push(applyOperator(operator, operand, 0));
        } else {
          if (operandStack.size() < 2) {
            throw new IllegalArgumentException(
                  "Invalid expression: not enough operands for operator " + operator);
          }

          int operand2 = operandStack.pop();
          int operand1 = operandStack.pop();
          operandStack.push(applyOperator(operator, operand1, operand2));
        }
      }
    }


    if (operandStack.size() != 1) {
      throw new IllegalArgumentException("Invalid expression");
    }

    return String.valueOf(operandStack.pop());
  }

  /**
   * Applies an operator to operands based on the operation type.
   *
   * @param operator The operator symbol to apply
   * @param operand1 The first operand (or the only operand for unary operations)
   * @param operand2 The second operand (ignored for unary operations)
   * @return The result of applying the operator to the operands
   * @throws IllegalArgumentException if the operator is unknown or unsupported
   * @throws ArithmeticException if a division by zero occurs
   */
  private int applyOperator(TokenOperatorType operator, int operand1, int operand2) {
    return switch (operator) {
      case ADD -> operand1 + operand2;
      case SUBTRACT -> operand1 - operand2;
      case MULTIPLY -> operand1 * operand2;
      case DIVIDE -> {
        if (operand2 == 0) {
          throw new ArithmeticException("Division by zero");
        }
        yield operand1 / operand2;
      }
      case BITWISE_OR -> operand1 | operand2;
      case BITWISE_XOR -> operand1 ^ operand2;
      case BITWISE_AND -> operand1 & operand2;
      case BITWISE_SHIFT_RIGHT -> operand1 >> operand2;
      case BITWISE_SHIFT_LEFT -> operand1 << operand2;
      case BITWISE_COMPLEMENT -> ~operand1;
      case PREFIX_DECREMENT -> operand1 - 1;
      case PREFIX_INCREMENT -> operand1 + 1;
      case POSTFIX_INCREMENT, POSTFIX_DECREMENT, POSITIVE -> operand1;
      case NEGATIVE -> -operand1;
      default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
    };
  }
}
