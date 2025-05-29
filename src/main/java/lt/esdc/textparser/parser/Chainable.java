package lt.esdc.textparser.parser;

public interface Chainable<T> {
  void setNext(T parser);

  /**
   * Links multiple parsers together, creating a chain of responsibility.
   *
   * @param first    the first parser in the chain
   * @param parsers  additional parsers to link
   * @return the first parser in the chain
   */
  static <T extends Chainable<T>> T link(T first, T... parsers) {
    T head = first;

    for (T parser : parsers) {
      head.setNext(parser);
      head = parser;
    }

    return first;
  }
}
