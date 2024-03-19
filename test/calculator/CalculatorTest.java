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
  public void testDigits() {
    Result result = calculator.addToken('1');

    assertEquals(34.0, result.getResult(), 0.00001);
    assertEquals("1", result.getFormula());

    result = calculator.addToken('2');
    assertEquals(34.0, result.getResult(), 0.00001);
    assertEquals("12", result.getFormula());
  }

  @Test
  public void testAdd() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('+');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.addToken('=');
    // assertEquals(46.0, result.getResult(), 0.00001);
    assertEquals("46.0", result.toString());
  }

  @Test
  public void testSubtract() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('-');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.addToken('=');
    assertEquals(-22.0, result.getResult(), 0.00001);
  }

  @Test
  public void testMultiply() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('*');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.addToken('=');
    assertEquals(408.0, result.getResult(), 0.00001);
  }

  @Test
  public void testDivide() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('/');
    calculator.addToken('0');
    calculator.addToken('6');
    Result result = calculator.addToken('=');
    assertEquals(2.0, result.getResult(), 0.00001);
  }

}