package formulabuilder;

import enums.TokenType;
import types.Token;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to build formulas.
 *
 * The formulas are returned as an array of tokens.  Each token is of type Token.
 */
public class FormulaBuilder {
  private List<Token> tokens;

  /**
   * This constructor is used to create a new FormulaBuilder object.
   */
  public FormulaBuilder() {
    tokens = new ArrayList<Token>();
  }


  /**
   * This method is used to add a character to the formula.
   * <p>
   * We discard all characters that are not numbers, operators, or parentheses.
   * <p></p>
   * If the incoming character is an operator or a parenthesis, add the current token to the list
   * If the incoming character is a number component, append the character to the current token
   * (the token takes care of the appending)
   *
   * @param c The character to add to the formula.
   */
  public void addToken(char c) throws InvalidObjectException {

    // If the incoming character is an operator or a parenthesis, add the current token to the list
    // create a new token appropriate for the incoming character

    // lets get rid of all the non legal characters
    if (!isNumberComponent(c) && !isOperator(c) && c != '(' && c != ')') {
      return;
    }

    if (isOperator(c)) {
      tokens.add(new Token(c, TokenType.Operator));
      return;
    }

    if (c == '(') {
      tokens.add(new Token(c, TokenType.LeftParenthesis));
      return;
    }

    if (c == ')') {
      tokens.add(new Token(c, TokenType.RightParenthesis));
      return;
    }

    // now we know that the incoming character is a number component
    // get the last token in the list, if there is none then make a new one


    Token lastToken = !tokens.isEmpty() ? tokens.get(tokens.size() - 1) : null;
    // If the last token is not a number then we start a new number
    if (lastToken == null || lastToken.getType() != TokenType.Number) {
      tokens.add(new Token(c, TokenType.Number));
      return;
    }

    // If the last token is a number then we append the incoming character to the last token
    try {
      lastToken.append(c);
    } catch (IllegalArgumentException e) {
      throw new InvalidObjectException("this should not happen");
    }


  }

  /**
   * deleteToken, remove the last token from the list.
   * If the last token is a number then we delete a character if there are some left
   * If the last token is an operator or a parenthesis then we remove the token
   */
  public void deleteToken() {
    if (tokens.isEmpty()) {
      return;
    }
    Token lastToken = tokens.get(tokens.size() - 1);
    if (lastToken.getType() == TokenType.Number) {
      try {
        lastToken.delete();
      } catch (IllegalArgumentException e) {
        throw new RuntimeException("this should not happen");
      }
      lastToken.delete();
      if (lastToken.getToken().isEmpty()) {
        tokens.remove(tokens.size() - 1);
      }
    } else {
      tokens.remove(tokens.size() - 1);
    }
  }

  private boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }

  private boolean isNumberComponent(char c) {
    if (c == '.') {
      return true;
    }
    return c >= '0' && c <= '9';
  }

  /**
   * return a list of tokens that represent the formula.
   *
   * @return a list of tokens that represent the formula.
   */
  public List<Token> getFormula() {
    return tokens;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Token token : tokens) {
      sb.append(token.getToken()).append(' ');
    }

    return sb.toString().strip();
  }

}
