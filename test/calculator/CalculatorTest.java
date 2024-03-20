package calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import types.Result;

public class CalculatorTest {

  private Calculator calculator;

  @Before
  public void setUp() {
    calculator = new Calculator();
  }

  @Test
  public void testEmpty() {
    Result result = calculator.evaluateFormula();

    assertEquals(0.0, result.getResult(), 0.00001);
    assertEquals("---", result.getFormula());
  }

  @Test
  public void testDigits() {
    calculator.addToken('1');
    Result result = calculator.evaluateFormula();

    assertEquals(1.0, result.getResult(), 0.00001);
    assertEquals("1", result.getFormula());
    assertEquals("", result.getError());

    calculator.addToken('2');
    result = calculator.evaluateFormula();
    assertEquals(12.0, result.getResult(), 0.00001);
    assertEquals("12", result.getFormula());
    assertEquals("", result.getError());
  }

  @Test
  public void testAdd() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('+');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.evaluateFormula();
    // assertEquals(46.0, result.getResult(), 0.00001);
    assertEquals(46.0, result.getResult(), 0.00001);
    assertEquals("12 + 34", result.getFormula());
    assertEquals("", result.getError());
  }

  @Test
  public void testSubtract() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('-');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.evaluateFormula();
    assertEquals(-22.0, result.getResult(), 0.00001);
  }

  @Test
  public void testMultiply() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('*');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.evaluateFormula();
    assertEquals(408.0, result.getResult(), 0.00001);
  }

  @Test
  public void testDivide() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('/');
    calculator.addToken('0');
    calculator.addToken('6');
    Result result = calculator.evaluateFormula();
    assertEquals(2.0, result.getResult(), 0.00001);
  }

  @Test
  public void testParentheses() {
    calculator.addToken('(');
    calculator.addToken('1');
    calculator.addToken('+');
    calculator.addToken('2');
    calculator.addToken(')');
    calculator.addToken('*');
    calculator.addToken('3');
    Result result = calculator.evaluateFormula();
    assertEquals(9.0, result.getResult(), 0.00001);
  }

}