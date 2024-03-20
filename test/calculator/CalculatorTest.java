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

  /**
   * Test that the calculator can handle digits.
   * the formula is 1 = 1
   * Then we add a second digit 2
   * the formula is 12 = 12
   */
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

  /**
   * Test that the calculator can handle addition.
   * the formula is 12 + 34 = 46
   */
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

  /**
   * Test that the calculator can handle subtraction.
   * the formula is 12 - 34 = -22
   */
  @Test
  public void testSubtract() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('-');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.evaluateFormula();
    assertEquals(-22.0, result.getResult(), 0.00001);
    assertEquals("12 - 34", result.getFormula());
    assertEquals("", result.getError());
  }

  /**
   * Test that the calculator can handle multiplication.
   * the formula is 12 * 34 = 408
   */
  @Test
  public void testMultiply() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('*');
    calculator.addToken('3');
    calculator.addToken('4');
    Result result = calculator.evaluateFormula();
    assertEquals(408.0, result.getResult(), 0.00001);
    assertEquals("12 * 34", result.getFormula());
    assertEquals("", result.getError());
  }

  /**
   * Test that the calculator can handle division.
   * the formula is 12 / 06 = 2
   */
  @Test
  public void testDivide() {
    calculator.addToken('1');
    calculator.addToken('2');
    calculator.addToken('/');
    calculator.addToken('0');
    calculator.addToken('6');
    Result result = calculator.evaluateFormula();
    assertEquals(2.0, result.getResult(), 0.00001);
    assertEquals("12 / 06", result.getFormula());
    assertEquals("", result.getError());
  }

  /**
   * Test that the calculator can handle parentheses.
   * the formula is (1+2)*3 = 9
   */
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
    assertEquals("( 1 + 2 ) * 3", result.getFormula());
    assertEquals("", result.getError());
  }

  /**
   * Test that the calculator can handle nested parenthesis.
   * the formula is (1+2)*(1+1) = 6
   */
  @Test
  public void testNestedParenthesis() {
    calculator.addToken('(');
    calculator.addToken('1');
    calculator.addToken('+');
    calculator.addToken('2');
    calculator.addToken(')');
    calculator.addToken('*');
    calculator.addToken('(');
    calculator.addToken('1');
    calculator.addToken('+');
    calculator.addToken('1');
    calculator.addToken(')');
    Result result = calculator.evaluateFormula();
    assertEquals(6.0, result.getResult(), 0.00001);
  }

}