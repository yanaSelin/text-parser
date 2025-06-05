package lt.esdc.textparser.parser.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.interpreter.ExpressionInterpreter;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MockitoTestNGListener.class)
public class ExpressionTextStructureParserTest {
  private AbstractTextComponentParser parser;

  @Mock
  private ExpressionInterpreter interpreter;

  @BeforeMethod
  public void setUp() {
    parser = new ExpressionTextStructureParser(interpreter);
  }

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
    MockParser mockParser = new MockParser();
    parser.setNext(mockParser);
    when(interpreter.interpret(anyString())).thenReturn("Mocked Result");

    // Act
    TextComponent result = parser.parse(sampleText);

    // Assert
    assertNotNull(result);
    assertTrue(result instanceof MockTextComponent);
    MockTextComponent mockTextComponent = (MockTextComponent) result;
    assertEquals(mockTextComponent.content(), "Mocked Result");
  }

  @Test(dataProvider = "notMatchingSamples")
  public void testTextParserIgnoreNotMatchingSamples(String sampleText) {
    // Arrange
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