package lt.esdc.textparser.service;

import java.util.List;
import java.util.Map;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.util.LetterUtil;

/**
 * Service interface for text processing operations.
 * Provides methods for analyzing, manipulating, and extracting information from
 * text components in a hierarchical text structure.
 */
public interface TextService {

  /**
   * Sorts components of a specified type based on the count of nested components of another type.
   *
   * @param text The root text component to process
   * @param type The type of components to sort
   * @param nestedType The type of nested components to count for sorting
   * @return A sorted list of text components
   */
  List<TextComponent> sortComponentsByNestedTypeCount(
        TextComponent text, TextComponentType type, TextComponentType nestedType);

  /**
   * Selects the component of a specified type
   * that contains the longest nested component of another type.
   *
   * @param text The root text component to process
   * @param type The type of components to search within
   * @param nestedType The type of nested components to measure length
   * @return The text component with the maximum length nested component
   * @throws java.util.NoSuchElementException if no components match the criteria
   */
  TextComponent selectComponentsWithNestedTypeMaxLength(
        TextComponent text, TextComponentType type, TextComponentType nestedType);

  /**
   * Removes components that contain fewer than the specified minimum count of nested components.
   *
   * @param text The root text component to modify
   * @param type The type of components to evaluate for removal
   * @param nestedType The type of nested components to count
   * @param minCount The minimum required count of nested components
   */
  void removeComponentsWithNestedTypeCountLessThan(
        TextComponent text, TextComponentType type, TextComponentType nestedType, int minCount);

  /**
   * Counts duplicate occurrences of components of the specified type.
   *
   * @param text The root text component to analyze
   * @param type The type of components to count duplicates of
   * @return A map with component string representation as key and occurrences count as value
   */
  Map<String, Integer> countDuplicates(TextComponent text, TextComponentType type);

  /**
   * Counts vowels and consonants in the text component.
   *
   * @param text The text component to analyze
   * @return A map with counts of vowels and consonants
   */
  Map<LetterUtil.LetterType, Integer> countVowelsAndConsonants(TextComponent text);
}
