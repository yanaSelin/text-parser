package lt.esdc.textparser.interpreter.impl;

/**
 * Enum representing operation types in the arithmetic expression interpreter.
 */
enum OperationType {
  // Arithmetic operators
  ADD("+", 5),
  SUBTRACT("-", 5),
  MULTIPLY("*", 6),
  DIVIDE("/", 6),

  // Bitwise operators
  BITWISE_OR("|", 1),
  BITWISE_XOR("^", 2),
  BITWISE_AND("&", 3),
  BITWISE_SHIFT_LEFT("<<", 4),
  BITWISE_SHIFT_RIGHT(">>", 4),
  BITWISE_COMPLEMENT("~", 7),

  // Parentheses
  LEFT_PARENTHESIS("(", 0),
  RIGHT_PARENTHESIS(")", 0);

  private final String symbol;

  private final int precedence;

  OperationType(String symbol, int precedence) {
    this.symbol = symbol;
    this.precedence = precedence;
  }

  public String getSymbol() {
    return symbol;
  }

  public int getPrecedence() {
    return precedence;
  }

  /**
   * Finds an operation type by its symbol.
   *
   * @param symbol the operation symbol
   * @return the corresponding OperationType, or null if not found
   */
  public static OperationType fromSymbol(String symbol) {
    for (OperationType operationType : values()) {
      if (operationType.getSymbol().equals(symbol)) {
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
  public static boolean isOperator(String token) {
    return fromSymbol(token) != null;
  }
}
