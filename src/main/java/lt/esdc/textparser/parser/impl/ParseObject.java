package lt.esdc.textparser.parser.impl;

public class ParseObject {
  private final String text;

  private int pointer;

  public ParseObject(String text) {
    this.text = text;
  }

  public void incrementPointer(int increment) {
    this.pointer += increment;
  }

  public String getText() {
    return text;
  }

  public int getPointer() {
    return pointer;
  }

  public boolean isDone() {
    return pointer >= text.length();
  }
}
