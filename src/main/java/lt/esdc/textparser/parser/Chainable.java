package lt.esdc.textparser.parser;

/**
 * Interface for chainable handlers.
 * This interface defines methods for setting the next handler in a chain
 * and linking multiple parsers together to form a chain of responsibility.
 *
 * @param <T> the type of the handler that can be chained
 */
public interface Chainable<T> {
  /**
   * Sets the next handler in the chain.
   *
   * @param handler the next handler to set
   */
  void setNext(T handler);

  /**
   * Links multiple parsers together, creating a chain of responsibility.
   *
   * @param first    the first parser in the chain
   * @param handlers  additional parsers to link
   * @return the first parser in the chain
   */
  static <T extends Chainable<T>> T link(T first, T... handlers) {
    T head = first;

    for (T handler : handlers) {
      head.setNext(handler);
      head = handler;
    }

    return first;
  }
}
