/* Generated By:JavaCC: Do not edit this line. BackEndVisitor.java Version 5.0 */
public interface BackEndVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTRoot node, Object data);
  public Object visit(ASTVar node, Object data);
  public Object visit(ASTConst node, Object data);
  public Object visit(ASTFBlock node, Object data);
  public Object visit(ASTFunc node, Object data);
  public Object visit(ASTReturn node, Object data);
  public Object visit(ASTParamList node, Object data);
  public Object visit(ASTParam node, Object data);
  public Object visit(ASTMain node, Object data);
  public Object visit(ASTSBlock node, Object data);
  public Object visit(ASTAssign node, Object data);
  public Object visit(ASTIfBlock node, Object data);
  public Object visit(ASTWhile node, Object data);
  public Object visit(ASTSkip node, Object data);
  public Object visit(ASTPlus node, Object data);
  public Object visit(ASTMinus node, Object data);
  public Object visit(ASTFuncArgs node, Object data);
  public Object visit(ASTNeg node, Object data);
  public Object visit(ASTTrue node, Object data);
  public Object visit(ASTFalse node, Object data);
  public Object visit(ASTOR node, Object data);
  public Object visit(ASTAND node, Object data);
  public Object visit(ASTEquiv node, Object data);
  public Object visit(ASTNotEquiv node, Object data);
  public Object visit(ASTLThan node, Object data);
  public Object visit(ASTLEThan node, Object data);
  public Object visit(ASTGTHAN node, Object data);
  public Object visit(ASTGeThan node, Object data);
  public Object visit(ASTArgList node, Object data);
  public Object visit(ASTType node, Object data);
  public Object visit(ASTLID node, Object data);
  public Object visit(ASTID node, Object data);
  public Object visit(ASTNum node, Object data);
}
/* JavaCC - OriginalChecksum=a3d88e71999bc3dd023695a89306f0ae (do not edit this line) */
