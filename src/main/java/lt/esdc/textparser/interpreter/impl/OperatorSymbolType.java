package lt.esdc.textparser.interpreter.impl;

/**
 * Enum representing operation types in the arithmetic expression interpreter.
 */
enum OperatorSymbolType {
  // Arithmetic operators
  PLUS("+"),
  MINUS("-"),
  MULTIPLY("*"),
  DIVIDE("/"),
  DECREMENT("--"),
  INCREMENT("++"),

  // Bitwise operators
  BITWISE_OR("|"),
  BITWISE_XOR("^"),
  BITWISE_AND("&"),
  BITWISE_SHIFT_LEFT("<<"),
  BITWISE_SHIFT_RIGHT(">>"),
  BITWISE_COMPLEMENT("~"),

  // Parentheses
  LEFT_PARENTHESIS("("),
  RIGHT_PARENTHESIS(")");

  private final String symbol;

  OperatorSymbolType(String symbol) {
    this.symbol = symbol;
  }

  boolean equals(String token) {
    return this.symbol.equals(token);
  }

  /**
   * Finds an operation type by its symbol.
   *
   * @param symbol the operation symbol
   * @return the corresponding OperationType, or null if not found
   */
  static OperatorSymbolType fromSymbol(String symbol) {
    for (OperatorSymbolType operationType : values()) {
      if (operationType.symbol.equals(symbol)) {
        return operationType;
      }
    }
    return null;
  }

  /**
   * Checks if a token is a valid operation symbol.
   *
   * @param token the token to check
   * @return true if the token is an operator symbol, false otherwise
   */
  static boolean isOperator(String token) {
    return fromSymbol(token) != null;
  }
}
