package types;

/**
 * Represents the result of a calculation.
 */
public class Result {
  private final double result;
  private final String formula;
  private final String error;


  /**
   * Constructs a new Result.
   *
   * @param result  the result of the calculation
   * @param formula the formula used to calculate the result
   * @param error   the error message if the calculation failed
   */
  public Result(double result, String formula, String error) {
    this.result = result;
    this.formula = formula;
    this.error = error;
  }

  /**
   * Gets the result of the calculation.
   *
   * @return the result of the calculation
   */
  public double getResult() {
    return result;
  }

  /**
   * Gets the formula used to calculate the result.
   *
   * @return the formula used to calculate the result
   */
  public String getFormula() {
    return formula;
  }

  /**
   * Gets the error message if the calculation failed.
   *
   * @return the error message if the calculation failed
   */
  public String getError() {
    return error;
  }
}
