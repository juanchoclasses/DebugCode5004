package calculator;

import enums.TokenType;
import formulabuilder.FormulaBuilder;
import types.Result;
import types.Token;

import java.io.IOException;
import java.util.List;

/**
 * Represents a calculator that can receive characters and return the result of the operation.
 */
public class Calculator implements CalculatorInterface {


  private FormulaBuilder formulaBuilder;

  private List<Token> workingFormula;

  /**
   * Constructs a calculator object.
   */
  public Calculator() {
    this.formulaBuilder = new FormulaBuilder();
  }

  @Override
  public void addToken(char token) {
    try {
      formulaBuilder.addToken(token);
    } catch (IOException e) {
      // do nothing, the exception is not thrown
    }

  }

  @Override
  public void deleteToken() {
    formulaBuilder.deleteToken();

  }

  @Override
  public void clear() {
    //formulaBuilder.clear();
  }

  /**
   * Recursive descent parser to evaluate the expression.
   * The grammar is:
   * expression = term | expression + term | expression - term
   * term = factor | term * factor | term / factor
   * factor = number | ( expression )
   * <p></p>
   * the tokens are of type Token and have the following types:
   * Token.Number
   * Token.Operator
   * Token.LeftParenthesis
   * Token.RightParenthesis
   *
   * @return the result of the expression currently in the formula
   */
  public Result evaluateFormula() {

    workingFormula = formulaBuilder.getFormula();
    // If the formula is empty, return 0
    if (this.workingFormula.isEmpty()) {
      return new Result(0.0, "---", "");
    }


    // call the recursive descent parser to evaluate the expression
    Result result = this.expression();
    return result;
  }

  /**
   * expression = term | expression + term | expression - term
   *
   * @return
   */
  private Result expression() {
    Result result = this.term();

    if (workingFormula.isEmpty()) {
      return result;
    }


    Token token = workingFormula.get(0);
    if (token.getType() == TokenType.Operator) {
      if (token.getToken().equals("+")) {
        workingFormula.remove(0);
        Result termResult = this.term();
        result = new Result(result.getResult() + termResult.getResult(), formulaBuilder.toString(), "");
      } else if (token.getToken().equals("-")) {
        workingFormula.remove(0);
        Result termResult = this.term();
        result = new Result(result.getResult() - termResult.getResult(), formulaBuilder.toString(), "");
      }
    }
    return result;
  }

  /**
   * term = factor | term * factor | term / factor.
   *
   * @return the result of the term
   */
  private Result term() {
    Result result = this.factor();
    if (workingFormula.isEmpty()) {
      return result;
    }

    Token token = workingFormula.get(0);
    if (token.getType() == TokenType.Operator) {
      if (token.getToken().equals("*")) {
        workingFormula.remove(0);
        Result factorResult = this.factor();
        result = new Result(result.getResult() * factorResult.getResult(), formulaBuilder.toString(), "");
      } else if (token.getToken().equals("/")) {
        workingFormula.remove(0);
        Result factorResult = this.factor();
        if (factorResult.getResult() == 0) {
          return new Result(0.0, formulaBuilder.toString(), "#DIV/0!");
        }
        result = new Result(result.getResult() / factorResult.getResult(), formulaBuilder.toString(), "");
      }
    }
    return result;
  }

  /**
   * factor = number | ( expression ).
   *
   * @return the result of the factor
   */
  private Result factor() {
    Token token = workingFormula.get(0);

    if (token.getType() == TokenType.Number) {
      workingFormula.remove(0);
      return new Result(token.getValue(), formulaBuilder.toString(), "");
    } else if (token.getType() == TokenType.LeftParenthesis) {
      workingFormula.remove(0);
      Result result = this.expression();
      if (workingFormula.isEmpty()) {
        return result;
      }
      token = workingFormula.get(0);
      if (token.getType() == TokenType.RightParenthesis) {
        workingFormula.remove(0);
        return result;
      }
    }
    return new Result(0.0, formulaBuilder.toString(), "Invalid expression");
  }


}
