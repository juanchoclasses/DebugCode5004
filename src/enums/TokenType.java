package enums;

public enum TokenType {

  Number("num"),
  Operator("op"),
  LeftParenthesis("("),
  RightParenthesis(")");
  private String display;

  TokenType(String display) {
    this.display = display;
  }


}
