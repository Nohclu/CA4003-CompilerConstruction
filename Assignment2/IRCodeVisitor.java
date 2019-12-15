import java.util.*;

public class IRCodeVisitor implements BackEndVisitor {
    private int tmp = 1;
    private int label = 1;
    // private boolean assignOpHit = false;

    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    public Object visit(ASTRoot node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTVar node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTConst node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTFBlock node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTFunc node, Object data) {
        String type = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String id = (String) node.jjtGetChild(1).jjtAccept(this, data);
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTReturn node, Object data) {
        String id = (String) node.jjtGetChild(0).jjtAccept(this, data);
        return node.value;
    }

    public Object visit(ASTParamList node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTParam node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTMain node, Object data) {
        System.out.println("main:");
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTSBlock node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTAssign node, Object data) {
        String val1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String val2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        System.out.println(String.format("\t%s = %s", val1, val2));
        return data;
    }

    public Object visit(ASTNeg node, Object data) {
        return node.value;
    }

    public Object visit(ASTIfBlock node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data); // Condition
        node.jjtGetChild(1).jjtAccept(this, data); // If Statement block
        node.jjtGetChild(2).jjtAccept(this, data); // Else Statement block
        return data;
    }

    public Object visit(ASTWhile node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data); // Condition
        node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    public Object visit(ASTSkip node, Object data) {
        return node.value;
    }

    public Object visit(ASTPlus node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s + %s", var1, var2);
    }

    public Object visit(ASTMinus node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s - %s", var1, var2);
    }

    public Object visit(ASTFuncArgs node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTTrue node, Object data) {
        return node.value;
    }

    public Object visit(ASTFalse node, Object data) {
        return node.value;
    }

    public Object visit(ASTOR node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s || %s", var1, var2);
    }

    public Object visit(ASTAND node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s && %s", var1, var2);
    }

    public Object visit(ASTEquiv node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s == %s", var1, var2);
    }

    public Object visit(ASTNotEquiv node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s != %s", var1, var2);
    }

    public Object visit(ASTLThan node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        return node.value = String.format("%s < %s", var1, var2);
    }

    public Object visit(ASTLEThan node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);

        return node.value = String.format("%s <= %s", var1, var2);
    }

    public Object visit(ASTGTHAN node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);

        return node.value = String.format("%s > %s", var1, var2);

    }

    public Object visit(ASTGeThan node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);

        return node.value = String.format("%s >= %s", var1, var2);
    }

    public Object visit(ASTArgList node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
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