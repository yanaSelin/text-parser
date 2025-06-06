package lt.esdc.textparser.parser.impl;

/**
 * A utility class that encapsulates the text being parsed
 * along with the current parsing position.
 * This class tracks the parsing progress
 * by maintaining a pointer to the current position in the text.
 * It allows parsers to incrementally process text
 * without modifying the original string.
 */
class ParseObject {
  private final String text;

  private int pointer;

  /**
   * Creates a new ParseObject with the provided text and initializes the pointer to zero.
   *
   * @param text the text to be parsed
   */
  public ParseObject(String text) {
    this.text = text;
  }

  /**
   * Advances the pointer by the specified increment.
   *
   * @param increment the number of characters to advance the pointer
   */
  public void incrementPointer(int increment) {
    this.pointer += increment;
  }

  /**
   * Returns the full text being parsed.
   *
   * @return the text string
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the current position in the text.
   *
   * @return the current pointer position
   */
  public int getPointer() {
    return pointer;
  }

  /**
   * Checks if the parsing is complete.
   *
   * @return true if the pointer has reached the end of the text, false otherwise
   */
  public boolean isDone() {
    return pointer >= text.length();
  }
}
