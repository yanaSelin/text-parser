package lt.esdc.textparser.service.impl;

import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import lt.esdc.textparser.composite.impl.TextStructure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.TextComponentType;
import lt.esdc.textparser.service.TextService;
import lt.esdc.textparser.util.LetterUtil.LetterType;

public class TextServiceImplTest {

  private TextService textService;

  @BeforeMethod
  public void setUp() {
    textService = new TextServiceImpl();
  }

  @Test
  public void testSortParagraphsByWordCount() {
    TextStructure text = TextComponentTestUtil.createText(new String[][]{
          {"Sunsets paint skies with beauty.", "Dreams inspire endless possibilities."},
          {"Hello world!"},
          {"Bright stars illuminate the calm night sky.", "Life blooms with hope and love."},
    });

    List<TextComponent> sortedParagraphs = textService.sortComponentsByNestedTypeCount(
          text, TextComponentType.PARAGRAPH, TextComponentType.WORD);

    assertEquals(sortedParagraphs.size(), 3);
    assertEquals(sortedParagraphs.get(0), text.getChildren().get(1));
    assertEquals(sortedParagraphs.get(1), text.getChildren().get(0));
    assertEquals(sortedParagraphs.get(2), text.getChildren().get(2));
  }

  @Test
  public void testSelectSentencesWithWordsMaxLength() {
    TextStructure text = TextComponentTestUtil.createText(new String[][]{
          {"Hello from there!"},
          {"This is a test."},
          {"Hello world?", "This is a longer sentence with many words."},
    });

    TextComponent sentenceWithLongestWord = textService.selectComponentsWithNestedTypeMaxLength(
          text, TextComponentType.SENTENCE, TextComponentType.WORD);

    TextStructure expectedParagraph = (TextStructure) text.getChildren().stream()
          .filter((child) -> child.getType() == TextComponentType.PARAGRAPH)
          .toList().get(2);
    TextStructure expectedSentence = (TextStructure) expectedParagraph.getChildren().stream()
          .filter((child) -> child.getType() == TextComponentType.SENTENCE)
          .toList().get(1);
    assertEquals(sentenceWithLongestWord, expectedSentence);
  }

  @Test
  public void testRemoveSentencesWithWordsLessThen() {
    TextStructure text = TextComponentTestUtil.createText(new String[][]{
          {"Hello from there!", "Waves crash softly against the shore.", "Dreams grow brighter with each sunrise."},
          {"Music fills hearts with pure joy.", "Hello world?"},
          {"Clouds drift across the sky.", "Love conquers all fears."},
    });

    textService.removeComponentsWithNestedTypeCountLessThan(
          text, TextComponentType.SENTENCE, TextComponentType.WORD, 4);

    List<TextComponent> paragraphs = text.getChildren().stream()
          .filter((child) -> child.getType() == TextComponentType.PARAGRAPH)
          .toList();
    TextStructure firstParagraph = (TextStructure) paragraphs.get(0);
    TextStructure secondParagraph = (TextStructure) paragraphs.get(1);
    TextStructure thirdParagraph = (TextStructure) paragraphs.get(2);

    List<TextComponent> firstParagraphSentences = firstParagraph.getChildren().stream()
          .filter((child) -> child.getType() == TextComponentType.SENTENCE)
          .toList();
    List<TextComponent> secondParagraphSentences = secondParagraph.getChildren().stream()
          .filter((child) -> child.getType() == TextComponentType.SENTENCE)
          .toList();
    List<TextComponent> thirdParagraphSentences = thirdParagraph.getChildren().stream()
          .filter((child) -> child.getType() == TextComponentType.SENTENCE)
          .toList();

    assertEquals(firstParagraphSentences.size(), 2);
    assertEquals(secondParagraphSentences.size(), 1);
    assertEquals(thirdParagraphSentences.size(), 2);
  }

  @Test
  public void testCountDuplicatesWords() {
    TextComponent text = TextComponentTestUtil.createText(new String[][]{{
          "The cat sat on the mat.",
          "The CAT was happy on the mat.",
          "The dog chased the cat."
    }});

    Map<String, Integer> wordCounts = textService.countDuplicates(text, TextComponentType.WORD);

    assertEquals(wordCounts.size(), 9, "Should have 9 unique words");
    assertEquals((int) wordCounts.get("the"), 6);
    assertEquals((int) wordCounts.get("cat"), 3);
    assertEquals((int) wordCounts.get("mat"), 2);
    assertEquals((int) wordCounts.get("on"), 2);
    assertEquals((int) wordCounts.get("sat"), 1);
    assertEquals((int) wordCounts.get("was"), 1);
    assertEquals((int) wordCounts.get("happy"), 1);
    assertEquals((int) wordCounts.get("dog"), 1);
    assertEquals((int) wordCounts.get("chased"), 1);
  }

  @Test
  public void testCountVowelsAndConsonants() {
    TextComponent text = TextComponentTestUtil.createText(new String[][]{{
          "The cat sat on the mat.",
          "The CAT was happy on the mat.",
          "The dog chased the cat."
    }});

    Map<LetterType, Integer> letterCounts = textService.countVowelsAndConsonants(text);

    assertEquals((int)letterCounts.get(LetterType.VOWEL), 20);
    assertEquals((int)letterCounts.get(LetterType.CONSONANT), 37);
  }
}
