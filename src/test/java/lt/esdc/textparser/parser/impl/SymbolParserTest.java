package lt.esdc.textparser.parser.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.composite.impl.Symbol;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SymbolParserTest {
  @DataProvider(name = "textSamples")
  public Object[][] provideTextSamples() {
    return new Object[][]{
          {"T"},
          {"\t"},
          {"\n"},
          {"."},
          {"$"},
          {"1"},
          {"я"},
          {" "},
    };
  }

  @DataProvider(name = "longSamples")
  public Object[][] provideLongSamples() {
    return new Object[][]{
          {"Td"},
          {"\ta"},
          {"\nsa"},
          {".2"},
          {"$w"},
          {"11"},
          {"яd"},
          {" a"},
    };
  }

  @Test(dataProvider = "textSamples")
  public void testTextParserParsesEntireContent(String sampleText) {
    // Arrange
    TextComponentParser parser = new SymbolParser();

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof Symbol);
    assertEquals(result.toString(), sampleText);
  }

  @Test(dataProvider = "longSamples")
  public void testTextParserCaptureOnlyFirst(String sampleText) {
    // Arrange
    TextComponentParser parser = new SymbolParser();

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof Symbol);
    assertEquals(result.toString(), String.valueOf(sampleText.charAt(0)));
  }
}