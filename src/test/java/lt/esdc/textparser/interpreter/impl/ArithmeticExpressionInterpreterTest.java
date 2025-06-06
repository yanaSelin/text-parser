package lt.esdc.textparser.interpreter.impl;

import static org.testng.Assert.assertEquals;

import lt.esdc.textparser.interpreter.ExpressionInterpreter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArithmeticExpressionInterpreterTest {

  private ExpressionInterpreter interpreter;

  @BeforeMethod
  public void setUp() {
    interpreter = new ArithmeticExpressionInterpreter();
  }

  @DataProvider(name = "arithmeticExpressions")
  public Object[][] provideArithmeticExpressions() {
    return new Object[][]{
          {"-5+3", "-2"},
          {"5+3", "8"},
          {"10-4", "6"},
          {"3*7", "21"},
          {"20/5", "4"},
          {"2+3*4", "14"},
          {"(2+3)*4", "20"},
          {"-(2+3)", "-5"},
          {"-(2+3)++", "-5"},
          {"(7+5*12*(2+5-2-71))/12", "-329"},  // Complex expression with parentheses
    };
  }

  @DataProvider(name = "unaryOperators")
  public Object[][] provideUnaryOperations() {
    return new Object[][]{
          {"++12", "13"},   // ++12 = 1 + 12 = 13
          {"++5", "6"},     // ++5 = 1 + 5 = 6
          {"--8", "7"},     // --8 = -1 + 8 = 7
          {"8--", "8"},
          {"8++", "8"},
          {"(8)++", "8"},
          {"(8)--", "8"},
          {"++(8)", "9"},
          {"--(8)", "7"},
          {"-8", "-8"},
          {"-(7)", "-7"},
          {"+(6)", "6"},
          {"+9", "9"},
    };
  }

  @DataProvider(name = "bitwiseOperations")
  public Object[][] provideBitwiseOperations() {
    return new Object[][]{
          {"5|3", "7"},        // 101 | 011 = 111 = 7
          {"5&3", "1"},        // 101 & 011 = 001 = 1
          {"5^3", "6"},        // 101 ^ 011 = 110 = 6
          {"3<<2", "12"},      // 011 << 2 = 1100 = 12
          {"3>>1", "1"},       // 011 >> 1 = 01 = 1
          {"~6", "-7"},        // ~00000110 = 11111001 = -7
          {"3>>5", "0"},       // 3 >> 5 = 0 (right shift by 5 positions)
          {"~6&9", "9"},       // ~6 & 9 = -7 & 9 = 2
          {"~6&9|(3&4)", "9"}  // (~6 & 9) | (3 & 4) = 2 | 0 = 2
    };
  }

  @DataProvider(name = "complexExpressions")
  public Object[][] provideComplexExpressions() {
    return new Object[][]{
          {"(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", "78"},
          {"(7^5|1&2<<(2|5>>2&71))|1200", "1202"},
          {"5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)", "5"},
          {"5*(1+2*(3/(4-(1-56-47)*73)+(-89+4/(42/7)))+1)", "-880"},
    };
  }

  @Test(dataProvider = "arithmeticExpressions")
  public void testArithmeticExpressions(String expression, String expectedResult) {
    String result = interpreter.interpret(expression);
    assertEquals(result, expectedResult);
  }

  @Test(dataProvider = "unaryOperators")
  public void testUnaryOperators(String expression, String expectedResult) {
    String result = interpreter.interpret(expression);
    assertEquals(result, expectedResult);
  }

  @Test(dataProvider = "bitwiseOperations")
  public void testBitwiseOperations(String expression, String expectedResult) {
    String result = interpreter.interpret(expression);
    assertEquals(result, expectedResult);
  }

  @Test(dataProvider = "complexExpressions")
  public void testComplexExpressions(String expression, String expectedResult) {
    String result = interpreter.interpret(expression);
    assertEquals(result, expectedResult);
  }

  @Test(expectedExceptions = ArithmeticException.class)
  public void testDivisionByZero() {
    interpreter.interpret("5/0");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInvalidExpression() {
    interpreter.interpret("5+*3");
  }
}
