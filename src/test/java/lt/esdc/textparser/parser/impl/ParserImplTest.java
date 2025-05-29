package lt.esdc.textparser.parser.impl;

import static org.testng.Assert.*;

import lt.esdc.textparser.composite.TextComponent;
import lt.esdc.textparser.parser.Parser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParserImplTest {
  @DataProvider(name = "textSamples")
  public Object[][] provideTextSamples() {
    return new Object[][]{
          {"light","""
\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!
\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?
\tIt is a established fact that a reader will be of a page when looking at its layout...
\tBye бандерлоги.""", """
\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!
\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?
\tIt is a established fact that a reader will be of a page when looking at its layout...
\tBye бандерлоги."""},
          {"arithmetic","""
\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -3-5 essentially 6*9/(3+4) unchanged. It was popularised in the 5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (-71+(2+3/(3*(2+1/2+2)-2)/10+2))/7 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
\tIt is a (7+5*12*(2+5-2-71))/12 established fact that a reader will be of a page when looking at its layout.
\tBye.""", """
\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining calculated essentially calculated unchanged. It was popularised in the calculated with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using calculated Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.
\tIt is a calculated established fact that a reader will be of a page when looking at its layout.
\tBye."""},
    };
  }

  @Test(dataProvider = "textSamples")
  public void testParse(String name, String inputText, String expectedOutput) {
    Parser parser = new ParserImpl();

    TextComponent component = parser.parse(inputText);

    assertEquals(component.toString(), expectedOutput);
  }
}