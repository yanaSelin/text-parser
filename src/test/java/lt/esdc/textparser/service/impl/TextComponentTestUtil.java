package lt.esdc.textparser.service.impl;

import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.composite.impl.Symbol;
import lt.esdc.textparser.composite.impl.TextStructure;

/**
 * Utility class for creating test TextComponent instances.
 */
public class TextComponentTestUtil {
  /**
   * Creates a sample text with specified content.
   *
   * @return A TextComponent with duplicate words
   */
  public static TextStructure createText(String[][] paragraphs) {
    TextStructure text = new TextStructure(TextComponentType.TEXT);

    for (String[] paragraphText : paragraphs) {
      TextStructure paragraph = new TextStructure(TextComponentType.PARAGRAPH);
      paragraph.addChild(new Symbol('\t'));

      for (String sentenceText : paragraphText) {
        TextStructure sentenceComponent = createSentence(sentenceText);
        paragraph.addChild(sentenceComponent);
        paragraph.addChild(new Symbol(' '));
      }

      paragraph.addChild(new Symbol('\n'));
      text.addChild(paragraph);
    }

    return text;
  }

  /**
   * Creates a sentence from the provided text.
   *
   * @param sentenceText The sentence text
   * @return A TextComponent of type SENTENCE
   */
  public static TextStructure createSentence(String sentenceText) {
    TextStructure sentence = new TextStructure(TextComponentType.SENTENCE);
    String[] words = sentenceText.split("\\s+");

    for (String wordText : words) {
      // Handle punctuation
      String cleanWord = wordText.replaceAll("[^a-zA-Z]", "");
      TextStructure word = new TextStructure(TextComponentType.WORD);

      // Add letters as symbols
      for (char c : cleanWord.toCharArray()) {
        word.addChild(new Symbol(c));
      }

      sentence.addChild(word);

      // Add punctuation if present
      if (!wordText.equals(cleanWord)) {
        char punctuation = wordText.charAt(wordText.length() - 1);
        if (!Character.isLetterOrDigit(punctuation)) {
          sentence.addChild(new Symbol(punctuation));
        }
      }
    }

    return sentence;
  }
}
