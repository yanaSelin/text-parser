package lt.esdc.textparser.service;

import java.util.List;
import java.util.Map;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.util.LetterUtil;

public interface TextService {
  List<TextComponent> sortComponentsByNestedTypeCount(
        TextComponent text, TextComponentType type, TextComponentType nestedType);

  TextComponent selectComponentsWithNestedTypeMaxLength(
        TextComponent text, TextComponentType type, TextComponentType nestedType);

  void removeComponentsWithNestedTypeCountLessThan(
        TextComponent text, TextComponentType type, TextComponentType nestedType, int minCount);

  Map<String, Integer> countDuplicates(TextComponent text, TextComponentType type);

  Map<LetterUtil.LetterType, Integer> countVowelsAndConsonants(TextComponent text);
}
