package lt.esdc.textparser.service.impl;

import lt.esdc.textparser.composite.TextComponent;
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

//    /**
//     * Creates a text component with specific vowel and consonant distribution.
//     *
//     * @return TextComponent with known letter counts
//     */
//    public static TextComponent createTextForVowelConsonantCount() {
//        TextComponent text = new TextStructure(TextComponentType.TEXT);
//
//        TextComponent paragraph = new TextStructure(TextComponentType.PARAGRAPH);
//        // This sentence has 11 vowels and 14 consonants (excluding punctuation)
//        TextComponent sentence = createSentence("Testing vowels and consonants.");
//
//        paragraph.addComponent(sentence);
//        text.addComponent(paragraph);
//
//        return text;
//    }

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

  /**
   * Creates a text with configurable paragraph and sentence structure.
   *
   * @param sentenceWordCounts 2D array specifying the number of words in each sentence
   * @return A constructed TextComponent
   */
  public static TextStructure createConfigurableText(int[][] sentenceWordCounts) {
    TextStructure text = new TextStructure(TextComponentType.TEXT);

    for (int p = 0; p < sentenceWordCounts.length; p++) {
      TextStructure paragraph = new TextStructure(TextComponentType.PARAGRAPH);

      for (int s = 0; s < sentenceWordCounts[p].length; s++) {
        TextStructure sentence = new TextStructure(TextComponentType.SENTENCE);
        int wordCount = sentenceWordCounts[p][s];

        for (int w = 0; w < wordCount; w++) {
          TextStructure word = new TextStructure(TextComponentType.WORD);
          // Create a word with length proportional to its position
          int wordLength = 3 + w % 7; // Words between 3-9 characters

          // Make one specific word very long for max length testing
          if (p == 2 && s == 1 && w == 2) {
            wordLength = 15; // Extra long word
          }

          for (int c = 0; c < wordLength; c++) {
            char letter = (char) ('a' + (c % 26));
            word.addChild(new Symbol(letter));
          }

          sentence.addChild(word);
        }

        // Add a period at the end
        sentence.addChild(new Symbol('.'));
        paragraph.addChild(sentence);
      }

      text.addChild(paragraph);
    }

    return text;
  }
}
