package lt.esdc.textparser.util;

/**
 * Utility class for symbol classification with letter detection and vowel/consonant checks.
 */
public final class LetterUtil {

  /**
   * Enum representing the type of letter.
   */
  public enum LetterType {
    VOWEL,
    CONSONANT,
  }

  private static final String VOWELS = "aeiouyAEIOUY";

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private LetterUtil() {
    throw new UnsupportedOperationException("Utility class should not be instantiated");
  }

  /**
   * Checks if the character is a letter.
   *
   * @param c Character to check
   * @return true if the character is a letter, false otherwise
   */
  public static boolean isLetter(char c) {
    return Character.isLetter(c);
  }

  /**
   * Checks if the character is a vowel.
   *
   * @param c Character to check
   * @return true if the character is a vowel, false otherwise
   */
  public static boolean isVowel(char c) {
    return VOWELS.indexOf(c) >= 0;
  }

  /**
   * Checks if the character is a consonant.
   *
   * @param c Character to check
   * @return true if the character is a letter and not a vowel, false otherwise
   */
  public static boolean isConsonant(char c) {
    return isLetter(c) && !isVowel(c);
  }
}
