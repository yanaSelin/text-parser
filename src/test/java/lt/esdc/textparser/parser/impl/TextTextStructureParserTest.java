package lt.esdc.textparser.parser.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.stream.Collectors;
import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.TextStructure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TextTextStructureParserTest {
  @DataProvider(name = "textSamples")
  public Object[][] provideTextSamples() {
    return new Object[][]{
          {"This is a sample text for testing."},
          {"\tAnother example of text.\n\tIt contains multiple lines."},
          {"Longer text that contains multiple sentences. This is the second sentence."},
          {"Text with special characters: !@#$%^&*()_+{}|:\"<>?[];',./`~"},
    };
  }

  @Test(dataProvider = "textSamples")
  public void testTextParserParsesEntireContent(String sampleText) {
    // Arrange
    TextComponentParser parser = new TextTextStructureParser();
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

  @Test
  public void testTextParserCreatesMultipleChildren() {
    // Arrange
    TextComponentParser parser = new TextTextStructureParser();
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
