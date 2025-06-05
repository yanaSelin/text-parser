package lt.esdc.textparser.interpreter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lt.esdc.textparser.util.StringUtil;

public class InfixToPostfixConverter {
  /**
   * Converts infix notation to postfix (Reverse Polish Notation) using the shunting yard algorithm
   */
  List<String> convert(List<String> tokens) {
    List<String> output = new ArrayList<String>();
    Stack<String> operators = new Stack<String>();

    for (String token : tokens) {
      if (StringUtil.isNumeric(token)) {
        // If token is a number, add it to output
        output.add(token);
      } else if (token.equals(OperationType.LEFT_PARENTHESIS.getSymbol())) {
        // If token is an opening parenthesis, push it to the stack
        operators.push(token);
      } else if (token.equals(OperationType.RIGHT_PARENTHESIS.getSymbol())) {
        // If token is a closing parenthesis, pop operators until opening parenthesis
        while (!operators.isEmpty() && !operators.peek().equals(OperationType.LEFT_PARENTHESIS.getSymbol())) {
          output.add(operators.pop());
        }
        if (!operators.isEmpty() && operators.peek().equals(OperationType.LEFT_PARENTHESIS.getSymbol())) {
          operators.pop(); // Discard the opening parenthesis
        }
      } else if (OperationType.isOperator(token)) {
        // If token is an operator, handle precedence
        while (!operators.isEmpty() && OperationType.isOperator(operators.peek()) &&
              getPrecedence(operators.peek()) >= getPrecedence(token)) {
          output.add(operators.pop());
        }
        operators.push(token);
      }
    }

    // Pop any remaining operators
    while (!operators.isEmpty()) {
      if (operators.peek().equals(OperationType.LEFT_PARENTHESIS.getSymbol())) {
        throw new IllegalArgumentException("Mismatched parentheses");
      }
      output.add(operators.pop());
    }

    return output;
  }

  /**
   * Gets the precedence of an operator
   */
  private int getPrecedence(String operator) {
    OperationType operationType = OperationType.fromSymbol(operator);
    return operationType != null ? operationType.getPrecedence() : -1;
  }
}