package lt.esdc.textparser.util;

/**
 * Utility class for string operations.
 */
public class StringUtil {
  private StringUtil() {
    throw new UnsupportedOperationException("Utility class should not be instantiated");
  }

  /**
   * Checks if a token is numeric.
   */
  public static boolean isNumeric(String token) {
    try {
      Integer.parseInt(token);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}