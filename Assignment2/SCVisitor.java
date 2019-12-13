import java.util.Hashtable;

public class SCVisitor implements BackEndVisitor {
private SymbolTable st; //scope
// private Hashtable <String, Boolean> usedDeclarations;

public SCVisitor() {
    super();
    this.st = new SymbolTable();
}

    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    @Override
    public Object visit(ASTRoot node, Object data) {
        System.out.println("\nStart of Root \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("Child: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }

        System.out.println("End of Root \n");
        return data;
    }

    public Object visit(ASTVar node, Object data) {
        System.out.println("Var Start...");
        String id = (String)node.jjtGetChild(0).jjtAccept(this,data);
        String type = (String)node.jjtGetChild(1).jjtAccept(this,data);
        // System.out.println("\nVar: "+ id + " " + type);
        this.st.addSymbol(new SymbolWrapper(id, null, type));
        return data;
    }

    public Object visit(ASTConst node, Object data) {
        System.out.println("Const Start...");
        String id = (String)node.jjtGetChild(0).jjtAccept(this,data);
        String type = (String)node.jjtGetChild(1).jjtAccept(this,data);
        String value = (String)node.jjtGetChild(2).jjtAccept(this,data);
        // System.out.println("\nConst: "+ id + " " + type + " "+ value);
        this.st.addSymbol(new SymbolWrapper(id, value, type));
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
        System.out.println("\nStart of Func \n");
        String type = (String)node.jjtGetChild(0).jjtAccept(this,data);
        String id = (String)node.jjtGetChild(1).jjtAccept(this,data);
        // System.out.println("\nFunc: "+ id + " " + type);
        this.st.addSymbol(new SymbolWrapper(id, null, type));
        this.st.openScope();

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("FChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }

        System.out.println("End of Func \n");
        return data;
    }

    public Object visit(ASTReturn node, Object data) {
        System.out.println("\nStart of Return \n");
        String id = (String)node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println("Ret: "+ id);
        System.out.println("End of Return \n");
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
            System.out.println ("MAinChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of Main \n");
        return data;
    }

    public Object visit(ASTSBlock node, Object data) {
        System.out.println("\nStart of SBlock \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("SBlockChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of SBlock \n");
        return data;
    }

    public Object visit(ASTAssign node, Object data) {
        System.out.println("\nStart of Assign \n");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            System.out.println ("ASSChild: "+node.jjtGetChild(i));
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        System.out.println("End of Assign \n");
        return data;
    }

    public Object visit(ASTIfBlock node, Object data) {
        System.out.println("\nStart of IfBlock \n");
        System.out.println(node.jjtGetNumChildren());
        System.out.println("End of IfBlock \n");
        return data;
    }

    public Object visit(ASTWhile node, Object data) {
        System.out.println("\nStart of While \n");
        System.out.println("End of While \n");
        return data;
    }

    public Object visit(ASTSkip node, Object data) {
        System.out.println("\nStart of Skip \n");
        System.out.println("End of Skip \n");
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
        System.out.println("End of FuncArgs \n");
        return data;
    }

    public Object visit(ASTNeg node, Object data) {
        return node.value;
    }

    public Object visit(ASTTrue node, Object data) {
        return node.value;
    }

    public Object visit(ASTFalse node, Object data) {
        return node.value;
    }

    public Object visit(ASTOR node, Object data) {
        return node.value;
    }

    public Object visit(ASTAND node, Object data) {
        return node.value;
    }

    public Object visit(ASTEquiv node, Object data) {
        return node.value;
    }

    public Object visit(ASTNotEquiv node, Object data) {
        return node.value;
    }

    public Object visit(ASTLThan node, Object data) {
        return node.value;
    }

    public Object visit(ASTLEThan node, Object data) {
        return node.value;
    }

    public Object visit(ASTGTHAN node, Object data) {
        return node.value;
    }

    public Object visit(ASTGeThan node, Object data) {
        return node.value;
    }

    public Object visit(ASTArgList node, Object data) {
        System.out.println("\nStart of ArgList \n");
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
}