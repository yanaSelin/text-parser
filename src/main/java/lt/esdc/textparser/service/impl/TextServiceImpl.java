package lt.esdc.textparser.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.service.TextService;
import lt.esdc.textparser.util.LetterUtil;

public class TextServiceImpl implements TextService {
  @Override
  public List<TextComponent> sortComponentsByNestedTypeCount(
        TextComponent text, TextComponentType type, TextComponentType nestedType) {
    return text.getComponents(type).stream()
          .sorted(Comparator.comparingInt((component) ->
                component.getComponents(nestedType).size()))
          .toList();
  }

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

  @Override
  public void removeComponentsWithNestedTypeCountLessThan(
        TextComponent text, TextComponentType type, TextComponentType nestedType, int minCount) {
    text.getComponents(type).stream()
          .filter(component -> component.getComponents(nestedType).size() < minCount)
          .forEach(text::removeComponent);
  }

  @Override
  public Map<String, Integer> countDuplicates(TextComponent text, TextComponentType type) {
    return text.getComponents(type).stream()
          .collect(Collectors.groupingBy(
                word -> word.toString().toLowerCase(),
                Collectors.summingInt(word -> 1)
          ));
  }

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
