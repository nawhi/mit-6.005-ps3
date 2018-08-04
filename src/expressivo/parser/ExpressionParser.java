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
    T__0=1, T__1=2, IDENT=3, INTEGER=4, DOT=5, SPACES=6;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_sum = 2, RULE_product = 3, 
    RULE_primitive = 4, RULE_number = 5;
  public static final String[] ruleNames = {
    "root", "expression", "sum", "product", "primitive", "number"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", null, null, "'.'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, "IDENT", "INTEGER", "DOT", "SPACES"
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
      setState(12);
      expression();
      setState(13);
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
    public SumContext sum() {
      return getRuleContext(SumContext.class,0);
    }
    public ProductContext product() {
      return getRuleContext(ProductContext.class,0);
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
      setState(18);
      switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(15);
        sum();
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(16);
        product();
        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        setState(17);
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

  public static class SumContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
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
    enterRule(_localctx, 4, RULE_sum);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(20);
      primitive();
      setState(21);
      match(T__0);
      setState(22);
      primitive();
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
    enterRule(_localctx, 6, RULE_product);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(24);
      primitive();
      setState(25);
      match(T__1);
      setState(26);
      primitive();
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
    enterRule(_localctx, 8, RULE_primitive);
    try {
      setState(30);
      switch (_input.LA(1)) {
      case IDENT:
        enterOuterAlt(_localctx, 1);
        {
        setState(28);
        match(IDENT);
        }
        break;
      case INTEGER:
      case DOT:
        enterOuterAlt(_localctx, 2);
        {
        setState(29);
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
    enterRule(_localctx, 10, RULE_number);
    int _la;
    try {
      setState(40);
      switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(32);
        match(INTEGER);
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(33);
        match(INTEGER);
        setState(34);
        match(DOT);
        setState(36);
        _la = _input.LA(1);
        if (_la==INTEGER) {
          {
          setState(35);
          match(INTEGER);
          }
        }

        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        setState(38);
        match(DOT);
        setState(39);
        match(INTEGER);
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

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\b-\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\3\3\5\3\25"+
      "\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\5\6!\n\6\3\7\3\7\3\7"+
      "\3\7\5\7\'\n\7\3\7\3\7\5\7+\n\7\3\7\2\2\b\2\4\6\b\n\f\2\2,\2\16\3"+
      "\2\2\2\4\24\3\2\2\2\6\26\3\2\2\2\b\32\3\2\2\2\n \3\2\2\2\f*\3\2\2"+
      "\2\16\17\5\4\3\2\17\20\7\2\2\3\20\3\3\2\2\2\21\25\5\6\4\2\22\25\5"+
      "\b\5\2\23\25\5\n\6\2\24\21\3\2\2\2\24\22\3\2\2\2\24\23\3\2\2\2\25"+
      "\5\3\2\2\2\26\27\5\n\6\2\27\30\7\3\2\2\30\31\5\n\6\2\31\7\3\2\2\2"+
      "\32\33\5\n\6\2\33\34\7\4\2\2\34\35\5\n\6\2\35\t\3\2\2\2\36!\7\5\2"+
      "\2\37!\5\f\7\2 \36\3\2\2\2 \37\3\2\2\2!\13\3\2\2\2\"+\7\6\2\2#$\7"+
      "\6\2\2$&\7\7\2\2%\'\7\6\2\2&%\3\2\2\2&\'\3\2\2\2\'+\3\2\2\2()\7\7"+
      "\2\2)+\7\6\2\2*\"\3\2\2\2*#\3\2\2\2*(\3\2\2\2+\r\3\2\2\2\6\24 &*";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}