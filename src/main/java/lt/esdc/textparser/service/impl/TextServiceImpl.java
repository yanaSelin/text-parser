package lt.esdc.textparser.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.service.TextService;
import lt.esdc.textparser.util.LetterUtil;

/**
 * Implementation of the {@link TextService} interface that provides
 * text processing operations using Java Stream API.
 */
public class TextServiceImpl implements TextService {

  /**
   * {@inheritDoc}
   * Implementation uses Java Stream API to sort components based on the count of
   * nested components of the specified type.
   * </p>
   */
  @Override
  public List<TextComponent> sortComponentsByNestedTypeCount(
        TextComponent text, TextComponentType type, TextComponentType nestedType) {
    return text.getComponents(type).stream()
          .sorted(Comparator.comparingInt((component) ->
                component.getComponents(nestedType).size()))
          .toList();
  }

  /**
   * {@inheritDoc}
   * Implementation finds the component that contains the nested component with maximum length
   * using Java Stream API's max operation.
   * </p>
   *
   * @throws java.util.NoSuchElementException if the text has no components of the specified type
   */
  @Override
  public TextComponent selectComponentsWithNestedTypeMaxLength(
        TextComponent text, TextComponentType type, TextComponentType nestedType) {
    return text.getComponents(type).stream()
          .max(Comparator.comparingInt((component) ->
                component.getComponents(nestedType).stream()
                      .mapToInt(TextComponent::length)
                      .max()
                      .orElse(0)))
          .orElseThrow();
  }

  /**
   * {@inheritDoc}
   * Implementation uses Java Stream API to filter and remove components that
   * have fewer nested components than the specified minimum count.
   * </p>
   */
  @Override
  public void removeComponentsWithNestedTypeCountLessThan(
        TextComponent text, TextComponentType type, TextComponentType nestedType, int minCount) {
    text.getComponents(type).stream()
          .filter(component -> component.getComponents(nestedType).size() < minCount)
          .forEach(text::removeComponent);
  }

  /**
   * {@inheritDoc}
   * Implementation uses Java Stream API's collectors to group components by their
   * lowercase string representation and count occurrences.
   * </p>
   */
  @Override
  public Map<String, Integer> countDuplicates(TextComponent text, TextComponentType type) {
    return text.getComponents(type).stream()
          .collect(Collectors.groupingBy(
                word -> word.toString().toLowerCase(),
                Collectors.summingInt(word -> 1)
          ));
  }

  /**
   * {@inheritDoc}
   * Implementation processes the text character by character to identify vowels and consonants,
   * then uses collectors to group and count them by type.
   * </p>
   */
  @Override
  public Map<LetterUtil.LetterType, Integer> countVowelsAndConsonants(TextComponent text) {
    return text.toString().chars()
          .mapToObj(c -> (char) c)
          .filter(LetterUtil::isLetter)
          .collect(Collectors.groupingBy(
                c -> LetterUtil.isVowel(c)
                      ? LetterUtil.LetterType.VOWEL
                      : LetterUtil.LetterType.CONSONANT,
                Collectors.summingInt(c -> 1)
          ));
  }
}
