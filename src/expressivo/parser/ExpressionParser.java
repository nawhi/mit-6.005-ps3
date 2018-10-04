// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    PLUS=1, TIMES=2, IDENT=3, INTEGER=4, DOT=5, SPACES=6;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_binop = 2, RULE_sum = 3, RULE_product = 4, 
    RULE_primitive = 5, RULE_number = 6;
  public static final String[] ruleNames = {
    "root", "expression", "binop", "sum", "product", "primitive", "number"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", null, null, "'.'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, "PLUS", "TIMES", "IDENT", "INTEGER", "DOT", "SPACES"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(14);
      expression();
      setState(15);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExpressionContext extends ParserRuleContext {
    public BinopContext binop() {
      return getRuleContext(BinopContext.class,0);
    }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public ExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expression; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
    }
  }

  public final ExpressionContext expression() throws RecognitionException {
    ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_expression);
    try {
      setState(19);
      switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(17);
        binop();
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(18);
        primitive();
        }
        break;
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class BinopContext extends ParserRuleContext {
    public SumContext sum() {
      return getRuleContext(SumContext.class,0);
    }
    public ProductContext product() {
      return getRuleContext(ProductContext.class,0);
    }
    public BinopContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_binop; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterBinop(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitBinop(this);
    }
  }

  public final BinopContext binop() throws RecognitionException {
    BinopContext _localctx = new BinopContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_binop);
    try {
      setState(23);
      switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(21);
        sum();
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(22);
        product();
        }
        break;
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class SumContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public List<TerminalNode> PLUS() { return getTokens(ExpressionParser.PLUS); }
    public TerminalNode PLUS(int i) {
      return getToken(ExpressionParser.PLUS, i);
    }
    public SumContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_sum; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterSum(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitSum(this);
    }
  }

  public final SumContext sum() throws RecognitionException {
    SumContext _localctx = new SumContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_sum);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(25);
      primitive();
      setState(30);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==PLUS) {
        {
        {
        setState(26);
        match(PLUS);
        setState(27);
        primitive();
        }
        }
        setState(32);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ProductContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public List<TerminalNode> TIMES() { return getTokens(ExpressionParser.TIMES); }
    public TerminalNode TIMES(int i) {
      return getToken(ExpressionParser.TIMES, i);
    }
    public ProductContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_product; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterProduct(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitProduct(this);
    }
  }

  public final ProductContext product() throws RecognitionException {
    ProductContext _localctx = new ProductContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_product);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(33);
      primitive();
      setState(38);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==TIMES) {
        {
        {
        setState(34);
        match(TIMES);
        setState(35);
        primitive();
        }
        }
        setState(40);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class PrimitiveContext extends ParserRuleContext {
    public TerminalNode IDENT() { return getToken(ExpressionParser.IDENT, 0); }
    public NumberContext number() {
      return getRuleContext(NumberContext.class,0);
    }
    public PrimitiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_primitive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPrimitive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPrimitive(this);
    }
  }

  public final PrimitiveContext primitive() throws RecognitionException {
    PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_primitive);
    try {
      setState(43);
      switch (_input.LA(1)) {
      case IDENT:
        enterOuterAlt(_localctx, 1);
        {
        setState(41);
        match(IDENT);
        }
        break;
      case EOF:
      case PLUS:
      case TIMES:
      case INTEGER:
      case DOT:
        enterOuterAlt(_localctx, 2);
        {
        setState(42);
        number();
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class NumberContext extends ParserRuleContext {
    public List<TerminalNode> INTEGER() { return getTokens(ExpressionParser.INTEGER); }
    public TerminalNode INTEGER(int i) {
      return getToken(ExpressionParser.INTEGER, i);
    }
    public TerminalNode DOT() { return getToken(ExpressionParser.DOT, 0); }
    public NumberContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_number; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterNumber(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitNumber(this);
    }
  }

  public final NumberContext number() throws RecognitionException {
    NumberContext _localctx = new NumberContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_number);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(46);
      _la = _input.LA(1);
      if (_la==INTEGER) {
        {
        setState(45);
        match(INTEGER);
        }
      }

      setState(54);
      _la = _input.LA(1);
      if (_la==DOT) {
        {
        setState(48);
        match(DOT);
        setState(50); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(49);
          match(INTEGER);
          }
          }
          setState(52); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==INTEGER );
        }
      }

      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\b;\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\3\3\3\5"+
      "\3\26\n\3\3\4\3\4\5\4\32\n\4\3\5\3\5\3\5\7\5\37\n\5\f\5\16\5\"\13"+
      "\5\3\6\3\6\3\6\7\6\'\n\6\f\6\16\6*\13\6\3\7\3\7\5\7.\n\7\3\b\5\b\61"+
      "\n\b\3\b\3\b\6\b\65\n\b\r\b\16\b\66\5\b9\n\b\3\b\2\2\t\2\4\6\b\n\f"+
      "\16\2\2;\2\20\3\2\2\2\4\25\3\2\2\2\6\31\3\2\2\2\b\33\3\2\2\2\n#\3"+
      "\2\2\2\f-\3\2\2\2\16\60\3\2\2\2\20\21\5\4\3\2\21\22\7\2\2\3\22\3\3"+
      "\2\2\2\23\26\5\6\4\2\24\26\5\f\7\2\25\23\3\2\2\2\25\24\3\2\2\2\26"+
      "\5\3\2\2\2\27\32\5\b\5\2\30\32\5\n\6\2\31\27\3\2\2\2\31\30\3\2\2\2"+
      "\32\7\3\2\2\2\33 \5\f\7\2\34\35\7\3\2\2\35\37\5\f\7\2\36\34\3\2\2"+
      "\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\t\3\2\2\2\" \3\2\2\2#(\5\f"+
      "\7\2$%\7\4\2\2%\'\5\f\7\2&$\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2"+
      ")\13\3\2\2\2*(\3\2\2\2+.\7\5\2\2,.\5\16\b\2-+\3\2\2\2-,\3\2\2\2.\r"+
      "\3\2\2\2/\61\7\6\2\2\60/\3\2\2\2\60\61\3\2\2\2\618\3\2\2\2\62\64\7"+
      "\7\2\2\63\65\7\6\2\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66"+
      "\67\3\2\2\2\679\3\2\2\28\62\3\2\2\289\3\2\2\29\17\3\2\2\2\n\25\31"+
      " (-\60\668";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}