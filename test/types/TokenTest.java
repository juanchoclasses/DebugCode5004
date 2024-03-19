package types;

import enums.TokenType;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TokenTest {

  private Token numberToken;
  private Token operatorToken;
  private Token leftParenthesisToken;
  private Token rightParenthesisToken;

  @Before
  public void setUp() {
    numberToken = new Token("1", TokenType.Number);
    operatorToken = new Token("+", TokenType.Operator);
    leftParenthesisToken = new Token("(", TokenType.LeftParenthesis);
    rightParenthesisToken = new Token(")", TokenType.RightParenthesis);


  }

  @Test
  public void testGetToken() {
  }

  @Test
  public void testGetType() {
  }

  @Test
  public void testSetString() {
  }

  @Test
  public void testAppend() {
    numberToken.append('2');
    assertEquals("12", numberToken.getToken());

    try {
      numberToken.append('a');
    } catch (IllegalArgumentException e) {
      assertEquals("Character is not a digit or period", e.getMessage());
    }

  }

  @Test
  public void testAppendPeriod() throws Exception {
    try {
      numberToken.append('.');
      assertEquals("1.", numberToken.getToken());
    } catch (IllegalArgumentException e) {
      throw new Exception("this should not happen");
    }
    try {
      numberToken.append('.');
    } catch (IllegalArgumentException e) {
      assertEquals("Number already contains a period", e.getMessage());
    }
  }

  @Test
  public void testDelete() {
    numberToken.append('1');
    assertEquals("11", numberToken.getToken());
    numberToken.delete();
    assertEquals("1", numberToken.getToken());
    numberToken.delete();
    assertEquals("", numberToken.getToken());
  }

  @Test
  public void testToString() {
  }
}