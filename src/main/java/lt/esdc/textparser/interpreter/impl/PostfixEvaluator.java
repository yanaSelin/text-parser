package lt.esdc.textparser.interpreter.impl;

import java.util.List;
import java.util.Stack;
import lt.esdc.textparser.util.StringUtil;

class PostfixEvaluator {
  /**
   * Evaluates a postfix expression
   */
  public String evaluate(List<String> postfix) {
    Stack<Integer> operandStack = new Stack<>();

    for (String token : postfix) {
      if (StringUtil.isNumeric(token)) {
        operandStack.push(Integer.parseInt(token));
      } else if (OperationType.isOperator(token)) {
        if (token.equals(OperationType.BITWISE_COMPLEMENT.getSymbol())) {
          // Unary operator
          int operand = operandStack.pop();
          operandStack.push(applyOperator(token, operand, 0));
        } else {
          // Binary operator
          int operand2 = operandStack.pop();
          int operand1 = operandStack.pop();
          operandStack.push(applyOperator(token, operand1, operand2));
        }
      }
    }

    if (operandStack.size() != 1) {
      throw new IllegalArgumentException("Invalid expression");
    }

    return String.valueOf(operandStack.pop());
  }

  /**
   * Applies an operator to operands
   */
  private int applyOperator(String operator, int operand1, int operand2) {
    OperationType operationType = OperationType.fromSymbol(operator);
    if (operationType == null) {
      throw new IllegalArgumentException("Unknown operator: " + operator);
    }

    return switch (operationType) {
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
      default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
    };
  }
}
