package lt.esdc.textparser.interpreter.impl;

import java.util.ArrayList;
import java.util.List;

class Tokenizer {
  /**
   * Tokenizes the expression into operators, operands, and parentheses
   */
  List<String> tokenize(String expression) {
    List<String> tokens = new ArrayList<>();
    StringBuilder currentToken = new StringBuilder();

    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (Character.isDigit(c)) {
        currentToken.append(c);
        while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
          currentToken.append(expression.charAt(i + 1));
          i++;
        }
        tokens.add(currentToken.toString());
        currentToken.setLength(0);
      } else {
        currentToken.append(c);
        while (i + 1 < expression.length() && !OperationType.isOperator(currentToken.toString())) {
          currentToken.append(expression.charAt(i + 1));
          i++;
        }

        tokens.add(currentToken.toString());
        currentToken.setLength(0);
      }
    }

    return tokens;
  }
}