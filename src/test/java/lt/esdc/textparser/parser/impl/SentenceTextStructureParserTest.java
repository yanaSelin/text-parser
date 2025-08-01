package lt.esdc.textparser.parser.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.stream.Collectors;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.TextStructure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SentenceTextStructureParserTest {
  @DataProvider(name = "textSamples")
  public Object[][] provideTextSamples() {
    return new Object[][]{
          {"This is a sample text for testing."},
          {"Another example of text \n that contains new lines!"},
          {"Text with special characters: !@#$%^&*()_+{}|:\"<>?[];',./`~ ..."},
          {"Русский текст."}
    };
  }

  @DataProvider(name = "notMatchingSamples")
  public Object[][] provideNotMatchingSamples() {
    return new Object[][]{
          {" This is a sample text for testing."},
          {"\tStarting from tab."},
          {"\nStarting from newline."},
          {"Without proper ending"},
          {"starting with lowercase?"},
    };
  }

  @Test(dataProvider = "textSamples")
  public void testTextParserParsesEntireContent(String sampleText) {
    // Arrange
    AbstractTextComponentParser parser = new SentenceTextStructureParser();
    MockParser mockParser = new MockParser();
    parser.setNext(mockParser);

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof TextStructure);
    TextStructure textStructure = (TextStructure) result;
    assertEquals(textStructure.getChildren().size(), 1);

    assertTrue(textStructure.getChildren().getFirst() instanceof MockTextComponent);
    MockTextComponent mockTextComponent = (MockTextComponent) textStructure.getChildren().getFirst();
    assertEquals(mockTextComponent.content(), sampleText);
  }

  @Test(dataProvider = "notMatchingSamples")
  public void testTextParserIgnoreNotMatchingSamples(String sampleText) {
    // Arrange
    AbstractTextComponentParser parser = new SentenceTextStructureParser();
    MockParser mockParser = new MockParser();
    parser.setNext(mockParser);

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof MockTextComponent);
    MockTextComponent mockTextComponent = (MockTextComponent) result;
    assertEquals(mockTextComponent.content(), sampleText);
  }

  @Test
  public void testTextParserCreatesMultipleChildren() {
    // Arrange
    AbstractTextComponentParser parser = new SentenceTextStructureParser();
    MockParser mockParser = new MockParser(5);
    parser.setNext(mockParser);

    String sampleText = "This is a longer text for testing multiple children.";

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof TextStructure);
    TextStructure textStructure = (TextStructure) result;

    int expectedChildCount = (int) Math.ceil((double) sampleText.length() / 5);
    assertEquals(textStructure.getChildren().size(), expectedChildCount);

    String actualContent = textStructure.getChildren().stream()
          .map(TextComponent::toString)
          .collect(Collectors.joining(""));
    assertEquals(actualContent, sampleText);
  }
}