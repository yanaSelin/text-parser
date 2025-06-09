package lt.esdc.textparser.interpreter.impl;

/**
 * Enum representing operator types in the arithmetic expression interpreter.
 * This enum defines the precedence and whether an operator is unary or binary.
 */
enum TokenOperatorType {
  // Arithmetic operators
  ADD(5),
  SUBTRACT(5),
  MULTIPLY(6),
  DIVIDE(6),
  PREFIX_DECREMENT(7, true),
  POSTFIX_DECREMENT(7, true),
  PREFIX_INCREMENT(7, true),
  POSTFIX_INCREMENT(7, true),
  NEGATIVE(7, true),
  POSITIVE(7, true),

  // Bitwise operators
  BITWISE_OR(1),
  BITWISE_XOR(2),
  BITWISE_AND(3),
  BITWISE_SHIFT_LEFT(4),
  BITWISE_SHIFT_RIGHT(4),
  BITWISE_COMPLEMENT(7, true),

  // Parentheses
  LEFT_PARENTHESIS(0),
  RIGHT_PARENTHESIS(0);

  private final int precedence;

  private final boolean isUnary;

  TokenOperatorType(int precedence, boolean isUnary) {
    this.precedence = precedence;
    this.isUnary = isUnary;
  }

  TokenOperatorType(int precedence) {
    this(precedence, false);
  }

  int getPrecedence() {
    return precedence;
  }

  boolean isUnary() {
    return isUnary;
  }

  boolean equals(String operator) {
    return this.name().equals(operator);
  }
}
