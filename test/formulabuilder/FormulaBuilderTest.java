package formulabuilder;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import types.Token;

import java.util.List;

public class FormulaBuilderTest {

  private FormulaBuilder formulaBuilder;

  @Before
  public void setUp() {
    formulaBuilder = new FormulaBuilder();
  }

  @Test
  public void testAdd() {
    try {
      formulaBuilder.addToken('1');
      formulaBuilder.addToken('+');
      formulaBuilder.addToken('2');
      formulaBuilder.addToken('3');

      List<Token> tokens = formulaBuilder.getFormula();
      assertEquals(3, tokens.size());
      assertEquals("1", tokens.get(0).getToken());
      assertEquals("+", tokens.get(1).getToken());
      assertEquals("23", tokens.get(2).getToken());
    } catch (Exception e) {
      fail("Exception thrown: " + e);
    }

  }

  @Test
  public void testMultiply() {
    try {
      formulaBuilder.addToken('1');
      formulaBuilder.addToken('*');
      formulaBuilder.addToken('2');
      formulaBuilder.addToken('3');

      List<Token> tokens = formulaBuilder.getFormula();
      assertEquals(3, tokens.size());
      assertEquals("1", tokens.get(0).getToken());
      assertEquals("*", tokens.get(1).getToken());
      assertEquals("23", tokens.get(2).getToken());
    } catch (Exception e) {
      fail("Exception thrown: " + e);
    }
  }

  @Test
  public void testParenthesis() {
    try {
      formulaBuilder.addToken('(');
      formulaBuilder.addToken('1');
      formulaBuilder.addToken('+');
      formulaBuilder.addToken('2');
      formulaBuilder.addToken(')');
      formulaBuilder.addToken('*');
      formulaBuilder.addToken('3');

      List<Token> tokens = formulaBuilder.getFormula();
      assertEquals(7, tokens.size());
      assertEquals("(", tokens.get(0).getToken());
      assertEquals("1", tokens.get(1).getToken());
      assertEquals("+", tokens.get(2).getToken());
      assertEquals("2", tokens.get(3).getToken());
      assertEquals(")", tokens.get(4).getToken());
    } catch (Exception e) {
      fail("Exception thrown: " + e);
    }
  }


}