public class SCVisitor implements BackEndVisitor {
    private SymbolTable st;

    public SCVisitor() {
        super();
        this.st = new SymbolTable();
    }

    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visit SimpleNode");
    }

    public Object visit(ASTRoot node, Object data) {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        // End of program
        this.st.closeScope();
        return data;
    }

    public Object visit(ASTVar node, Object data) {
        String id = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String type = (String) node.jjtGetChild(1).jjtAccept(this, data);
        if (dupDecl(id)) {
            throw new java.lang.Error(String.format("ERROR: Multiple declarations of %s", id));
        } else {
            this.st.addSymbol(new SymbolWrapper(id, null, type));
        }
        return data;
    }

    public Object visit(ASTConst node, Object data) {
        String id = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String type = (String) node.jjtGetChild(1).jjtAccept(this, data);
        String value = (String) node.jjtGetChild(2).jjtAccept(this, data);
        if (dupDecl(id)) {
            throw new java.lang.Error(String.format("ERROR: Multiple declarations of %s", id));
        } else if (getBaseType(value).equals(type)) {
            this.st.addSymbol(new SymbolWrapper(id, value, type));
            return data;
        } else {
            throw new java.lang.Error(
                    String.format("ERROR: Cannot assign %s to ID(%s) of type %s", value, id, getBaseType(value)));
        }
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
        this.st.addSymbol(new SymbolWrapper(id, null, type));
        this.st.openScope();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
        this.st.closeScope();
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
        this.st.openScope();
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(this, data);
        }
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
        String lhs = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String rhs = (String) node.jjtGetChild(1).jjtAccept(this, data);
        SymbolWrapper lhsSW = this.st.getSymbol(lhs);
        if (lhsSW == null) {
            throw new java.lang.Error(String.format("ERROR: ID(%s) does not exist", lhs));
        } else {
            if (lhsSW.type.equals(getBaseType(rhs))) {
                lhsSW.value = rhs;
                return data;
            } else {
                throw new java.lang.Error(String.format("ERROR: Cannot assign %s to %s", rhs, lhs));
            }
        }
        
    }

    public Object visit(ASTNeg node, Object data) {
        String val = (String)node.jjtGetChild(0).jjtAccept(this, data);
        SymbolWrapper sw = this.st.getSymbol(val);
        if(sw.type.equals("boolean")){ 
            if (Boolean.parseBoolean(sw.value) == true) {
                return (node.value = "false");
            } else {
                return (node.value = "true");
            }
        }else{
            return (node.value =(Integer.toString(-1*Integer.parseInt(sw.value))));
            }
    }

    public Object visit(ASTIfBlock node, Object data) {
        System.out.println("Start of if");
        node.jjtGetChild(0).jjtAccept(this, data); //Condition
        this.st.openScope();
        node.jjtGetChild(1).jjtAccept(this, data); //If Statement block
        this.st.closeScope();
        this.st.openScope();
        node.jjtGetChild(2).jjtAccept(this, data); //Else Statement block
        this.st.closeScope();
        System.out.println("End of if");
        return data;
    }

    public Object visit(ASTWhile node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, data); //Condition
        this.st.openScope();
        node.jjtGetChild(0).jjtAccept(this, data);
        this.st.closeScope();
        return data;
    }

    public Object visit(ASTSkip node, Object data) {
        return data;
    }

    public Object visit(ASTPlus node, Object data) {
        int ret = 0;

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            String n = (String) node.jjtGetChild(i).jjtAccept(this, data);
            SymbolWrapper sw = this.st.getSymbol(n);
            if (isNumber(n)) {
                ret += Integer.parseInt(n);
            } else if (sw != null) {
                if (sw.type.equals("boolean")) {
                    throw new java.lang.Error(String.format("ERROR: Cannot add type boolean"));
                } else {
                    ret += Integer.parseInt(sw.value);
                    sw.used = true;
                }
            } else {
                throw new java.lang.Error(String.format("ERROR: ID(%s) does not exist", n));
            }
        }
        return (Integer.toString(ret));
    }

    public Object visit(ASTMinus node, Object data) {
        int ret = 0;

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            String n = (String) node.jjtGetChild(i).jjtAccept(this, data);
            SymbolWrapper sw = this.st.getSymbol(n);
            if (isNumber(n)) {
                ret -= Integer.parseInt(n);
            } else if (sw != null) {
                if (sw.type.equals("boolean")) {
                    throw new java.lang.Error(String.format("ERROR: Cannot subtract type boolean"));
                } else {
                    ret -= Integer.parseInt(sw.value);
                    sw.used = true;
                }
            } else {
                throw new java.lang.Error(String.format("ERROR: ID(%s) does not exist", n));
            }
        }
        return (Integer.toString(ret));
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
        String bool1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String bool2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        if (!(getBaseType(bool1).equals("boolean") && getBaseType(bool2).equals("boolean"))) {
            throw new java.lang.Error(String.format("ERROR: Was expecting bool || bool"));
        }
        return Boolean.toString(Boolean.parseBoolean(bool1) || Boolean.parseBoolean(bool2));
    }

    public Object visit(ASTAND node, Object data) {
        String bool1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String bool2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        if (!(getBaseType(bool1).equals("boolean") && getBaseType(bool2).equals("boolean"))) {
            throw new java.lang.Error(String.format("ERROR: Was expecting bool && bool"));
        }
        return Boolean.toString(Boolean.parseBoolean(bool1) && Boolean.parseBoolean(bool2));
    }

    public Object visit(ASTEquiv node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        String v1Type = getBaseType(var1);
        String v2Type = getBaseType(var2);
        if ((v1Type.equals(v2Type)) && (v1Type != null || v2Type != null)) {
            return Boolean.toString(var1 == var2);
        } else {
            throw new java.lang.Error(String.format("ERROR: Mismatched types"));
        }
    }

    public Object visit(ASTNotEquiv node, Object data) {
        String var1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String var2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        String v1Type = getBaseType(var1);
        String v2Type = getBaseType(var2);
        if ((v1Type.equals(v2Type)) && (v1Type != null || v2Type != null)) {
            return Boolean.toString(var1 != var2);
        } else {
            throw new java.lang.Error(String.format("ERROR: Mismatched types"));
        }
    }

    public Object visit(ASTLThan node, Object data) {
        String num1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        SymbolWrapper sw1 = this.st.getSymbol(num1);
        SymbolWrapper sw2 = this.st.getSymbol(num2);
        if (isNumber(num1) && isNumber(num2)) {
            return Boolean.toString(Integer.parseInt(num1) < Integer.parseInt(num2));
        } else if (sw1 != null && sw2 != null) {
            if (sw1.type.equals("integer") && sw2.type.equals("integer")) {
                sw1.used = true;
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) < Integer.parseInt(sw2.value));
            }
        } else if (sw1 != null && isNumber(num2)) {
            if (sw1.type.equals("integer")) {
                sw1.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) < Integer.parseInt(num2));
            }
        } else if (sw2 != null && isNumber(num1)) {
            if (sw2.type.equals("integer")) {
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(num1) < Integer.parseInt(sw2.value));
            }
        }
        throw new java.lang.Error(String.format("ERROR: Was expecting type integer < type integer"));
    }

    public Object visit(ASTLEThan node, Object data) {
        String num1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        SymbolWrapper sw1 = this.st.getSymbol(num1);
        SymbolWrapper sw2 = this.st.getSymbol(num2);
        if (isNumber(num1) && isNumber(num2)) {
            return Boolean.toString(Integer.parseInt(num1) <= Integer.parseInt(num2));
        } else if (sw1 != null && sw2 != null) {
            if (sw1.type.equals("integer") && sw2.type.equals("integer")) {
                sw1.used = true;
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) <= Integer.parseInt(sw2.value));
            }
        } else if (sw1 != null && isNumber(num2)) {
            if (sw1.type.equals("integer")) {
                sw1.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) <= Integer.parseInt(num2));
            }
        } else if (sw2 != null && isNumber(num1)) {
            if (sw2.type.equals("integer")) {
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(num1) <= Integer.parseInt(sw2.value));
            }
        }
        throw new java.lang.Error(String.format("ERROR: Was expecting type integer <= type integer"));
    }

    public Object visit(ASTGTHAN node, Object data) {
        String num1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        SymbolWrapper sw1 = this.st.getSymbol(num1);
        SymbolWrapper sw2 = this.st.getSymbol(num2);
        if (isNumber(num1) && isNumber(num2)) {
            return Boolean.toString(Integer.parseInt(num1) > Integer.parseInt(num2));
        } else if (sw1 != null && sw2 != null) {
            if (sw1.type.equals("integer") && sw2.type.equals("integer")) {
                sw1.used = true;
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) > Integer.parseInt(sw2.value));
            }
        } else if (sw1 != null && isNumber(num2)) {
            if (sw1.type.equals("integer")) {
                sw1.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) > Integer.parseInt(num2));
            }
        } else if (sw2 != null && isNumber(num1)) {
            if (sw2.type.equals("integer")) {
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(num1) > Integer.parseInt(sw2.value));
            }
        }
        throw new java.lang.Error(String.format("ERROR: Was expecting type integer > type integer"));
    }

    public Object visit(ASTGeThan node, Object data) {
        String num1 = (String) node.jjtGetChild(0).jjtAccept(this, data);
        String num2 = (String) node.jjtGetChild(1).jjtAccept(this, data);
        SymbolWrapper sw1 = this.st.getSymbol(num1);
        SymbolWrapper sw2 = this.st.getSymbol(num2);
        if (isNumber(num1) && isNumber(num2)) {
            return Boolean.toString(Integer.parseInt(num1) >= Integer.parseInt(num2));
        } else if (sw1 != null && sw2 != null) {
            if (sw1.type.equals("integer") && sw2.type.equals("integer")) {
                sw1.used = true;
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) >= Integer.parseInt(sw2.value));
            }
        } else if (sw1 != null && isNumber(num2)) {
            if (sw1.type.equals("integer")) {
                sw1.used = true;
                return Boolean.toString(Integer.parseInt(sw1.value) >= Integer.parseInt(num2));
            }
        } else if (sw2 != null && isNumber(num1)) {
            if (sw2.type.equals("integer")) {
                sw2.used = true;
                return Boolean.toString(Integer.parseInt(num1) >= Integer.parseInt(sw2.value));
            }
        }
        throw new java.lang.Error(String.format("ERROR: Was expecting type integer > type integer"));
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

    private boolean dupDecl(String id) {
        return this.st.searchScope(id);
    }

    private String getBaseType(String s) {
        if (isNumber(s)) {
            return "integer";
        }
        if (isBool(s)) {
            return "boolean";
        }
        SymbolWrapper sw = this.st.getSymbol(s);
        if (sw != null) {
            sw.used = true;
            return sw.type;
        }
        return null;
    }

    private boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isBool(String s) {
        if (s == null) {
            return false;
        }
        return "true".equals(s) || "false".equals(s);
    }
}