package formulabuilder;

import enums.TokenType;
import types.Token;

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
   *
   */
  public FormulaBuilder() {
    tokens = new ArrayList<Token>();
  }

  public void addCharacter(char c) {

    // If the incoming character is an operator or a parenthesis, add the current token to the list
    // create a new token appropriate for the incoming character

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
    lastToken.append(c);


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

}
