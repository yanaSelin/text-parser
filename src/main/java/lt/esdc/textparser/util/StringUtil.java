package lt.esdc.textparser.util;

/**
 * Utility class for string operations.
 */
public class StringUtil {
  private static final String NUMERIC_PATTERN = "^\\d+$";

  private StringUtil() {
    throw new UnsupportedOperationException("Utility class should not be instantiated");
  }

  /**
   * Checks if a token is numeric.
   */
  public static boolean isNumeric(String token) {
    return token.matches(NUMERIC_PATTERN);
  }
}