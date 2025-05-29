package lt.esdc.textparser.parser.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import lt.esdc.textparser.composite.TextComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExpressionTextStructureParserTest {
  @DataProvider(name = "textSamples")
  public Object[][] provideTextSamples() {
    return new Object[][]{
          {"(7+5*12*(2+5-2-71))/12"},
          {"++12"},
          {"3>>5"},
          {"~6&9|(3&4)"},
          {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78"},
          {"(7^5|1&2<<(2|5>>2&71))|1200"},
          {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)"}
    };
  }

  @DataProvider(name = "notMatchingSamples")
  public Object[][] provideNotMatchingSamples() {
    return new Object[][]{
          {" ++12"},
          {"Another example (7+5*12*(2+5-2-71))/12"},
    };
  }

  @Test(dataProvider = "textSamples")
  public void testTextParserParsesEntireContent(String sampleText) {
    // Arrange
    TextComponentParser parser = new ExpressionTextStructureParser();
    MockParser mockParser = new MockParser();
    parser.setNext(mockParser);

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof MockTextComponent);
    MockTextComponent mockTextComponent = (MockTextComponent) result;
    assertEquals(mockTextComponent.content(), "calculated");
  }

  @Test(dataProvider = "notMatchingSamples")
  public void testTextParserIgnoreNotMatchingSamples(String sampleText) {
    // Arrange
    TextComponentParser parser = new ExpressionTextStructureParser();
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
}