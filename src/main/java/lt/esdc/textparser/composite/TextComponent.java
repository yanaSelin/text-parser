package lt.esdc.textparser.composite;

import java.util.List;

/**
 * Interface representing a component in the composite text structure.
 * Implements the Composite design pattern for text parsing and processing.
 */
public interface TextComponent {
  /**
   * Gets the type of this text component.
   *
   * @return the component type
   */
  TextComponentType getType();

  /**
   * Gets the length of this text component.
   *
   * @return the length of the component
   */
  int length();

  /**
   * Retrieves all child components of a specific type.
   *
   * @param type the type of components to retrieve
   * @return a list of child components matching the specified type
   */
  List<TextComponent> getComponents(TextComponentType type);

  /**
   * Removes a child component from this component.
   *
   * @param component the component to remove
   * @return true if the component was removed successfully, false otherwise
   */
  boolean removeComponent(TextComponent component);
}
