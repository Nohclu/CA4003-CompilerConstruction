public class IrCodeVisitor implements BackEndVisitor
{
    @Override
    public Object visit(SimpleNode node, Object data){
        throw new RuntimeException("Visit SimpleNode");
    }
    @Override
    public Object visit(ASTRoot node, Object data){
        node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println(";");
        System.out.println(node.jjtGetChild(0).jjtAccept(this, data));
        return data;
    }

    public Object visit(ASTVar node, Object data)
    {return data;}
    public Object visit(ASTConst node, Object data)
    {return data;}
    public Object visit(ASTFBlock node, Object data)
    {return data;}
    public Object visit(ASTFunc node, Object data)
    {return data;}
    public Object visit(ASTReturn node, Object data)
    {return data;}
    public Object visit(ASTParamList node, Object data)
    {return data;}
    public Object visit(ASTParam node, Object data)
    {return data;}
    public Object visit(ASTMain node, Object data)
    {return data;}
    public Object visit(ASTSBlock node, Object data)
    {return data;}
    public Object visit(ASTAssign node, Object data)
    {return data;}
    public Object visit(ASTIfBlock node, Object data)
    {return data;}
    public Object visit(ASTWhile node, Object data)
    {return data;}
    public Object visit(ASTSkip node, Object data)
    {return data;}
    public Object visit(ASTPlus node, Object data)
    {return data;}
    public Object visit(ASTMinus node, Object data)
    {return data;}
    public Object visit(ASTFuncArgs node, Object data)
    {return data;}
    public Object visit(ASTNeg node, Object data)
    {return data;}
    public Object visit(ASTTrue node, Object data)
    {return data;}
    public Object visit(ASTFalse node, Object data)
    {return data;}
    public Object visit(ASTOR node, Object data)
    {return data;}
    public Object visit(ASTAND node, Object data)
    {return data;}
    public Object visit(ASTEquiv node, Object data)
    {return data;}
    public Object visit(ASTNotEquiv node, Object data)
    {return data;}
    public Object visit(ASTLThan node, Object data)
    {return data;}
    public Object visit(ASTLEThan node, Object data)
    {return data;}
    public Object visit(ASTGTHAN node, Object data)
    {return data;}
    public Object visit(ASTGeThan node, Object data)
    {return data;}
    public Object visit(ASTArgList node, Object data)
    {return data;}
    public Object visit(ASTType node, Object data)
    {return data;}
    public Object visit(ASTID node, Object data)
    {return data;}
    public Object visit(ASTNum node, Object data)
    {return data;}
    public Object visit(ASTLID node, Object data){return data;}
}