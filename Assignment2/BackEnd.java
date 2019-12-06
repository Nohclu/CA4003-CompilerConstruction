/* Generated By:JJTree&JavaCC: Do not edit this line. BackEnd.java */
    public class BackEnd/*@bgen(jjtree)*/implements BackEndTreeConstants, BackEndConstants {/*@bgen(jjtree)*/
  protected static JJTBackEndState jjtree = new JJTBackEndState();public static void main(String[] args){
            BackEnd parser;
            if (args.length == 0) {
                System.out.println("BackEnd Parser: Reading from standard input . . .");
                parser = new BackEnd(System.in);
                } else if (args.length == 1) {
                    System.out.println("BackEnd Parser: Reading from file " + args[0] + " . . .");
                    try {
                        parser = new BackEnd(new java.io.FileInputStream(args[0]));
                    } catch (java.io.FileNotFoundException e) {
                        System.out.println("BackEnd Parser: File " + args[0] + " not found.");
                        return;
                }
            } else {
                    System.out.println("BackEnd Parser: Usage is one of:");
                    System.out.println(" java BackEndParser < inputfile");
                    System.out.println("OR");
                    System.out.println(" java BackEndParser inputfile");
                    return;
            }
            try {
                SimpleNode root = parser.Program();
                root.dump("");
                System.out.println("CCAL Parser: CCAL program parsed successfully.");
                } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println("CCAL Parser: Encountered errors during parse.");
            }
        }

  static final public SimpleNode Program() throws ParseException {
                                /*@bgen(jjtree) Program */
  SimpleNode jjtn000 = new SimpleNode(JJTPROGRAM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      DeclList();
      FunctionList();
      Main();
      jj_consume_token(0);
                                             jjtree.closeNodeScope(jjtn000, true);
                                             jjtc000 = false;
                                            {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public void DeclList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
    case CONST:
      Decl();
      jj_consume_token(SEMIC);
      DeclList();
      break;
    default:
      jj_la1[0] = jj_gen;

    }
  }

  static final public void Decl() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      VarDecl();
      break;
    case CONST:
      ConstDecl();
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void VarDecl() throws ParseException {
    jj_consume_token(VAR);
    jj_consume_token(ID);
    jj_consume_token(COLON);
    Type();
  }

  static final public void ConstDecl() throws ParseException {
    jj_consume_token(CONST);
    jj_consume_token(ID);
    jj_consume_token(COLON);
    Type();
    jj_consume_token(ASSIGN);
    Expression();
  }

  static final public void FunctionList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case VOID:
    case INTEGER:
      Function();
      FunctionList();
      break;
    default:
      jj_la1[2] = jj_gen;

    }
  }

  static final public void Function() throws ParseException {
    Type();
    jj_consume_token(ID);
    jj_consume_token(LPAR);
    ParameterList();
    jj_consume_token(RPAR);
    jj_consume_token(LBRC);
    DeclList();
    StatementBlock();
    jj_consume_token(RETURN);
    jj_consume_token(LPAR);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case LPAR:
    case MINUS_SIGN:
    case NUMBER:
    case ID:
      Expression();
      break;
    default:
      jj_la1[3] = jj_gen;

    }
    jj_consume_token(RPAR);
    jj_consume_token(SEMIC);
    jj_consume_token(RBRC);
  }

  static final public void Type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      jj_consume_token(INTEGER);
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
      break;
    case VOID:
      jj_consume_token(VOID);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ParameterList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      NempParameterList();
      break;
    default:
      jj_la1[5] = jj_gen;

    }
  }

  static final public void NempParameterList() throws ParseException {
    jj_consume_token(ID);
    jj_consume_token(COLON);
    Type();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      NempParameterList();
      break;
    default:
      jj_la1[6] = jj_gen;

    }
  }

  static final public void Main() throws ParseException {
                    /*@bgen(jjtree) Main */
  SimpleNode jjtn000 = new SimpleNode(JJTMAIN);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(MAIN);
      jj_consume_token(LBRC);
      DeclList();
      StatementBlock();
      jj_consume_token(RBRC);
    } catch (Throwable jjte000) {
      if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
      if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  static final public void StatementBlock() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
    case WHILE:
    case SKP:
    case LBRC:
    case ID:
      Statement();
      StatementBlock();
      break;
    default:
      jj_la1[7] = jj_gen;

    }
  }

  static final public void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ASSIGN:
        jj_consume_token(ASSIGN);
        Expression();
        jj_consume_token(SEMIC);
        break;
      case LPAR:
        jj_consume_token(LPAR);
        ArgList();
        jj_consume_token(RPAR);
        jj_consume_token(SEMIC);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case LBRC:
      jj_consume_token(LBRC);
      StatementBlock();
      jj_consume_token(RBRC);
      break;
    case IF:
      jj_consume_token(IF);
      Condition();
      jj_consume_token(LBRC);
      StatementBlock();
      jj_consume_token(RBRC);
      jj_consume_token(ELSE);
      jj_consume_token(LBRC);
      StatementBlock();
      jj_consume_token(RBRC);
      break;
    case WHILE:
      jj_consume_token(WHILE);
      Condition();
      jj_consume_token(LBRC);
      StatementBlock();
      jj_consume_token(RBRC);
      break;
    case SKP:
      jj_consume_token(SKP);
      jj_consume_token(SEMIC);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Expression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case MINUS_SIGN:
    case NUMBER:
    case ID:
      Fragment();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS_SIGN:
      case MINUS_SIGN:
        BinaryArithOp();
        Fragment();
        break;
      default:
        jj_la1[10] = jj_gen;

      }
      break;
    case LPAR:
      jj_consume_token(LPAR);
      Expression();
      jj_consume_token(RPAR);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void BinaryArithOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS_SIGN:
      jj_consume_token(PLUS_SIGN);
      break;
    case MINUS_SIGN:
      jj_consume_token(MINUS_SIGN);
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Fragment() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS_SIGN:
    case ID:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MINUS_SIGN:
        jj_consume_token(MINUS_SIGN);
        break;
      default:
        jj_la1[13] = jj_gen;

      }
      jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAR:
        jj_consume_token(LPAR);
        ArgList();
        jj_consume_token(RPAR);
        break;
      default:
        jj_la1[14] = jj_gen;

      }
      FragmentPrime();
      break;
    case NUMBER:
      jj_consume_token(NUMBER);
      FragmentPrime();
      break;
    case TRUE:
      jj_consume_token(TRUE);
      FragmentPrime();
      break;
    case FALSE:
      jj_consume_token(FALSE);
      FragmentPrime();
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void FragmentPrime() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS_SIGN:
    case MINUS_SIGN:
      BinaryArithOp();
      Expression();
      FragmentPrime();
      break;
    default:
      jj_la1[16] = jj_gen;

    }
  }

  static final public void Condition() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TILDE:
      jj_consume_token(TILDE);
      Condition();
      ConditionPrime();
      break;
    case LPAR:
      jj_consume_token(LPAR);
      Condition();
      jj_consume_token(RPAR);
      ConditionPrime();
      break;
    case TRUE:
    case FALSE:
    case MINUS_SIGN:
    case NUMBER:
    case ID:
      Fragment();
      CompOp();
      Expression();
      ConditionPrime();
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ConditionPrime() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OR:
    case AND:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        jj_consume_token(OR);
        break;
      case AND:
        jj_consume_token(AND);
        break;
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Condition();
      ConditionPrime();
      break;
    default:
      jj_la1[19] = jj_gen;

    }
  }

  static final public void CompOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUIV:
      jj_consume_token(EQUIV);
      break;
    case NOTEQUIV:
      jj_consume_token(NOTEQUIV);
      break;
    case LTHAN:
      jj_consume_token(LTHAN);
      break;
    case LETHAN:
      jj_consume_token(LETHAN);
      break;
    case GTHAN:
      jj_consume_token(GTHAN);
      break;
    case GETHAN:
      jj_consume_token(GETHAN);
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ArgList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      NempArgList();
      break;
    default:
      jj_la1[21] = jj_gen;

    }
  }

  static final public void NempArgList() throws ParseException {
    jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      NempArgList();
      break;
    default:
      jj_la1[22] = jj_gen;

    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public BackEndTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[23];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x6,0x6,0x2030,0x900600,0x2030,0x0,0x4000,0x41880,0x120000,0x41880,0xc00000,0x900600,0xc00000,0x800000,0x100000,0x800600,0xc00000,0x1900600,0x6000000,0x6000000,0xf8000000,0x0,0x4000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x18,0x0,0x10,0x0,0x10,0x0,0x10,0x0,0x18,0x0,0x0,0x0,0x18,0x0,0x18,0x0,0x0,0x1,0x10,0x0,};
   }

  /** Constructor with InputStream. */
  public BackEnd(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public BackEnd(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new BackEndTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public BackEnd(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new BackEndTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public BackEnd(BackEndTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(BackEndTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[47];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 23; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 47; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

    }