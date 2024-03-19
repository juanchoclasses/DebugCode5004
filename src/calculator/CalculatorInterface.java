package calculator;

import types.Result;

/**
 * The a digit based calculator interface.
 */
public interface CalculatorInterface {

  /**
   * This method is used to add a token to the calculator.
   *
   * @param c the token to add.
   * @return the result of adding the token.
   */
  public Result addToken(char c);

  /**
   * This method is used to delete a token from the calculator.
   *
   * @return the result of deleting the token.
   */
  public Result deleteToken();

  /**
   * This method is used to clear the calculator.
   */

  public void clear();
}
