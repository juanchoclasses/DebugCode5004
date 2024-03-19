package calculator;

import formulabuilder.FormulaBuilder;
import types.Result;

import java.io.IOException;

/**
 * Represents a calculator that can receive characters and return the result of the operation.
 */
public class Calculator implements CalculatorInterface {


  private FormulaBuilder formulaBuilder;

  /**
   * Constructs a calculator object.
   */
  public Calculator() {
    this.formulaBuilder = new FormulaBuilder();
  }

  @Override
  public Result addToken(char token) {
    try {
      formulaBuilder.addToken(token);
    } catch (IOException e) {
      // do nothing, the exception is not thrown
    }


    Result result = new Result(34.0, formulaBuilder.toString(), "");
    return result;
  }

  @Override
  public Result deleteToken() {
    formulaBuilder.deleteToken();

    Result result = new Result(34.0, "34.0", "");
    return result;
  }

  @Override
  public void clear() {
    //formulaBuilder.clear();
  }


}
