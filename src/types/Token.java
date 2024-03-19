package types;

import enums.TokenType;

/**
 * This class is used to represent a token.  In addition to this it handles the
 * appending of characters to the token.
 * <p></p>
 * Appending a character to a token is only allowed if the token is a number.
 */
public class Token {
  private String token;
  private final TokenType type;

  public Token(String token, TokenType type) {
    this.token = token;
    this.type = type;
  }

  public Token(char c, TokenType type) {
    this.token = String.valueOf(c);
    this.type = type;
  }

  public String getToken() {
    return token;
  }

  public TokenType getType() {
    return type;
  }

  public void setString(String token) {
    this.token = token;
  }

  /**
   * Appends a character to the token.
   * If the token is not a number then an exception is thrown.
   * If the character is not a digit or a period then an exception is thrown.
   * If the character is a digit then it is appended to the token.
   * If the character is a period and the token does not contain a period then it is
   * appended to the token.
   * If the character is a period and the token contains a period then an exception is thrown.
   *
   * @param c the character to append
   */
  public void append(char c) throws IllegalArgumentException {
    // if the token is not a number then throw an exception
    if (type != TokenType.Number) {
      throw new IllegalArgumentException("Token is not a number");
    }

    if (!Character.isDigit(c) && c != '.') {
      throw new IllegalArgumentException("Character is not a digit or period");
    }

    // if the character is a digit then append it to the token
    if (Character.isDigit(c)) {
      token += c;
      return;
    }

    // if the character is a period and there is a period then throw an exception
    if (c == '.' && token.contains(".")) {
      throw new IllegalArgumentException("Number already contains a period");
    }

    // if the character is a period then append it to the token
    if (c == '.') {
      token += c;
    }
  }

  /**
   * Deletes the last character from the token.
   * If the token is not a number then an exception is thrown.
   * If the token is empty then nothing happens.
   */
  public void delete() {
    if (type != TokenType.Number) {
      throw new IllegalArgumentException("Token is not a number");
    }
    if (!token.isEmpty()) {
      token = token.substring(0, token.length() - 1);
    }

  }

  @Override
  public String toString() {
    return token;
  }
}
