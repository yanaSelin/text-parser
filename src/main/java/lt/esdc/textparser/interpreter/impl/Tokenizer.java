package lt.esdc.textparser.interpreter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lt.esdc.textparser.util.StringUtil;

/**
 * Responsible for breaking down a mathematical expression string into individual tokens.
 *
 * <p>This class separates an expression into distinct parts (tokens) such as numbers,
 * operators, and parentheses to facilitate processing by the expression interpreter.
 * It handles multi-digit numbers and various operator symbols.</p>
 */
class Tokenizer {
  /**
   * Tokenizes the expression into individual components (operators, operands, and parentheses).
   *
   * @param expression The mathematical expression string to tokenize
   * @return A list of tokens extracted from the expression
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

        StringBuilder nextToken = new StringBuilder();
        nextToken.append(c);

        while (i + 1 < expression.length()) {
          char nextChar = expression.charAt(i + 1);
          nextToken.append(nextChar);
          if (OperatorSymbolType.isOperator(nextToken.toString())) {
            currentToken.append(nextChar);
            i++;
          } else {
            break;
          }
        }

        tokens.add(getOperator(currentToken.toString(), tokens.isEmpty()
              ? Optional.empty()
              : Optional.of(tokens.getLast())));
        currentToken.setLength(0);
      }
    }

    return tokens;
  }

  private boolean isOperand(Optional<String> token) {
    return token.isPresent()
          && (StringUtil.isNumeric(token.get())
          || TokenOperatorType.RIGHT_PARENTHESIS.equals(token.get()));
  }

  private String getOperator(String token, Optional<String> prevToken) {
    OperatorSymbolType operator = OperatorSymbolType.fromSymbol(token);

    if (operator == null) {
      throw new IllegalArgumentException("Unknown operator: " + operator);
    }

    return switch (operator) {
      case PLUS -> isOperand(prevToken)
            ? TokenOperatorType.ADD.name()
            : TokenOperatorType.POSITIVE.name();
      case MINUS -> isOperand(prevToken)
            ? TokenOperatorType.SUBTRACT.name()
            : TokenOperatorType.NEGATIVE.name();
      case MULTIPLY -> TokenOperatorType.MULTIPLY.name();
      case DIVIDE -> TokenOperatorType.DIVIDE.name();
      case BITWISE_OR -> TokenOperatorType.BITWISE_OR.name();
      case BITWISE_XOR -> TokenOperatorType.BITWISE_XOR.name();
      case BITWISE_AND -> TokenOperatorType.BITWISE_AND.name();
      case BITWISE_SHIFT_RIGHT -> TokenOperatorType.BITWISE_SHIFT_RIGHT.name();
      case BITWISE_SHIFT_LEFT -> TokenOperatorType.BITWISE_SHIFT_LEFT.name();
      case BITWISE_COMPLEMENT -> TokenOperatorType.BITWISE_COMPLEMENT.name();
      case DECREMENT -> isOperand(prevToken)
            ? TokenOperatorType.POSTFIX_DECREMENT.name()
            : TokenOperatorType.PREFIX_DECREMENT.name();
      case INCREMENT -> isOperand(prevToken)
            ? TokenOperatorType.POSTFIX_INCREMENT.name()
            : TokenOperatorType.PREFIX_INCREMENT.name();
      case LEFT_PARENTHESIS -> TokenOperatorType.LEFT_PARENTHESIS.name();
      case RIGHT_PARENTHESIS -> TokenOperatorType.RIGHT_PARENTHESIS.name();
    };
  }
}