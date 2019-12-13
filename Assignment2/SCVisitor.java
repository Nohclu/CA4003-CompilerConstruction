public class SCVisitor implements BackEndVisitor {
private SymbolTable st; //scope
// private Hashtable <String, Boolean> usedDeclarations;

public SCVisitor() {
    super();
    this.st = new SymbolTable();
}

    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    public Object visit(ASTRoot node, Object data) {
        System.out.println("\nStart of Root \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("Child: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of Root \n");
        this.st.closeScope();
        return data;
    }

    public Object visit(ASTVar node, Object data) {
        String id = (String)node.jjtGetChild(0).jjtAccept(this,data);
        String type = (String)node.jjtGetChild(1).jjtAccept(this,data);
        //Check ID Doesnt exist in stack
        if (dupDecl(id)) {
            throw new java.lang.Error(String.format("ERROR: Multiple declarations of %s", id));
        } else {
            this.st.addSymbol(new SymbolWrapper(id, null, type));
        }
        return data;
    }

    public Object visit(ASTConst node, Object data) {
        String id = (String)node.jjtGetChild(0).jjtAccept(this,data);
        String type = (String)node.jjtGetChild(1).jjtAccept(this,data);
        String value = (String)node.jjtGetChild(2).jjtAccept(this,data);
        // Check type and value are the same
        //Check ID Doesnt exist in stack
        if (dupDecl(id)) {
            throw new java.lang.Error(String.format("ERROR: Multiple declarations of %s", id));
        } else if(assignTypeCheck(type, value)) {
            throw new java.lang.Error(String.format("ERROR: Cannot assign %s to ID(%s) of type %s", value, id, type));
        }else {
            this.st.addSymbol(new SymbolWrapper(id, value, type));
        }
        return data;
    }

    public Object visit(ASTFBlock node, Object data) {
        System.out.println("\nStart of FBlock \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("FBChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of FBlock \n");
        return data;
    }

    public Object visit(ASTFunc node, Object data) {
        String type = (String)node.jjtGetChild(0).jjtAccept(this,data);
        String id = (String)node.jjtGetChild(1).jjtAccept(this,data);
        System.out.println(String.format("Func: %s %s", type, id));
        //Check ID doesnt Exist in stack
        this.st.addSymbol(new SymbolWrapper(id, null, type));
        this.st.openScope();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        this.st.closeScope();;
        return data;
    }

    public Object visit(ASTReturn node, Object data) {
        String id = (String)node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println("Ret: "+ id);
        return node.value;
    }

    public Object visit(ASTParamList node, Object data) {
        System.out.println("\nStart of ParamList \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("PLChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of ParamList \n");
        return data;
    }

    public Object visit(ASTParam node, Object data) {
        System.out.println("\nStart of Param \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("PChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of Param \n");
        return data;
    }

    public Object visit(ASTMain node, Object data) {
        this.st.openScope();
        System.out.println("\nStart of Main \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of Main \n");
        this.st.closeScope();
        return data;
    }

    public Object visit(ASTSBlock node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTAssign node, Object data) {
        String id = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String val = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("Assign: %s %s", id, val));
        return data;
    }

    public Object visit(ASTIfBlock node, Object data) {
        System.out.println("IF Block");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("IF Block");
        return data;
    }

    public Object visit(ASTWhile node, Object data) {
        System.out.println("While Block");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("While Block");
        return data;
    }

    public Object visit(ASTSkip node, Object data) {
        return data;
    }

    public Object visit(ASTPlus node, Object data) {
        return node.value;
    }

    public Object visit(ASTMinus node, Object data) {
        return node.value;
    }

    public Object visit(ASTFuncArgs node, Object data) {
        System.out.println("\nStart of FuncArgs \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("FuncArgs: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of FuncArgs \n");
        return data;
    }

    public Object visit(ASTNeg node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("Neg: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return node.value;
    }

    public Object visit(ASTTrue node, Object data) {
        return node.value;
    }

    public Object visit(ASTFalse node, Object data) {
        return node.value;
    }

    public Object visit(ASTOR node, Object data) {
        String bool1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String bool2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("LThan: %s %s", bool1, bool2));
        return data;
    }

    public Object visit(ASTAND node, Object data) {
        String bool1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String bool2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("LThan: %s %s", bool1, bool2));
        return data;
    }

    public Object visit(ASTEquiv node, Object data) {
        String var1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("LThan: %s %s", var1, var2));
        return data;
    }

    public Object visit(ASTNotEquiv node, Object data) {
        String var1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("LThan: %s %s", var1, var2));
        return data;
    }

    public Object visit(ASTLThan node, Object data) {
        String num1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("LThan: %s %s", num1, num2));
        return data;
    }

    public Object visit(ASTLEThan node, Object data) {
        String num1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("LEThan: %s %s", num1, num2));
        return data;
    }

    public Object visit(ASTGTHAN node, Object data) {
        String num1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("GThan: %s %s", num1, num2));
        return data;
    }

    public Object visit(ASTGeThan node, Object data) {
        String num1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("GEThan: %s %s", num1, num2));
        return data;
    }

    public Object visit(ASTArgList node, Object data) {
        System.out.println("\nStart of ArgList \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("FuncArgs: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of ArgList \n");
        return data;
    }

    public Object visit(ASTType node, Object data) {
        return node.value;
    }

    public Object visit(ASTID node, Object data) {
        return node.value;
    }

    public Object visit(ASTNum node, Object data) {
        return node.value;
    }

    public Object visit(ASTLID node, Object data) {
        return node.value;
    }

    private boolean assignTypeCheck(String v1, String v2){
        if (isNumber(v1) && isNumber(v2) ||
                isBool(v1) && isBool(v2)) {
            return true;
        }
        return false;
    }
    private boolean compOpTypeCheck(String v1, String v2){
        // GThan LEthan etc
        return true;
    }
    private boolean dupDecl(String id){
        // Check no repeating decls in same scope
        return this.st.searchScope(id);
    }
    private boolean declRead(){
        //When popping from stack check all values have been read from
        return true;
    }
    private boolean varWrite(){
        return true;
    }

    private boolean isNumber(String s){
        if(s == null){
            return false;
        }
        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private boolean isBool(String s){
        if(s == null){
            return false;
        }
        return Boolean.parseBoolean(s);
    }
}