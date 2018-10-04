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
    PLUS=1, TIMES=2, LPAREN=3, RPAREN=4, IDENT=5, INTEGER=6, DOT=7, SPACES=8;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_term = 2, RULE_atom = 3, RULE_primitive = 4, 
    RULE_number = 5;
  public static final String[] ruleNames = {
    "root", "expression", "term", "atom", "primitive", "number"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", "'('", "')'", null, null, "'.'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, "PLUS", "TIMES", "LPAREN", "RPAREN", "IDENT", "INTEGER", "DOT", 
    "SPACES"
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
    public List<TermContext> term() {
      return getRuleContexts(TermContext.class);
    }
    public TermContext term(int i) {
      return getRuleContext(TermContext.class,i);
    }
    public List<TerminalNode> PLUS() { return getTokens(ExpressionParser.PLUS); }
    public TerminalNode PLUS(int i) {
      return getToken(ExpressionParser.PLUS, i);
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
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(15);
      term();
      setState(20);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==PLUS) {
        {
        {
        setState(16);
        match(PLUS);
        setState(17);
        term();
        }
        }
        setState(22);
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

  public static class TermContext extends ParserRuleContext {
    public List<AtomContext> atom() {
      return getRuleContexts(AtomContext.class);
    }
    public AtomContext atom(int i) {
      return getRuleContext(AtomContext.class,i);
    }
    public List<TerminalNode> TIMES() { return getTokens(ExpressionParser.TIMES); }
    public TerminalNode TIMES(int i) {
      return getToken(ExpressionParser.TIMES, i);
    }
    public TermContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_term; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterTerm(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitTerm(this);
    }
  }

  public final TermContext term() throws RecognitionException {
    TermContext _localctx = new TermContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_term);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(23);
      atom();
      setState(28);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==TIMES) {
        {
        {
        setState(24);
        match(TIMES);
        setState(25);
        atom();
        }
        }
        setState(30);
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

  public static class AtomContext extends ParserRuleContext {
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public TerminalNode LPAREN() { return getToken(ExpressionParser.LPAREN, 0); }
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public TerminalNode RPAREN() { return getToken(ExpressionParser.RPAREN, 0); }
    public AtomContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_atom; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAtom(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAtom(this);
    }
  }

  public final AtomContext atom() throws RecognitionException {
    AtomContext _localctx = new AtomContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_atom);
    try {
      setState(36);
      switch (_input.LA(1)) {
      case EOF:
      case PLUS:
      case TIMES:
      case RPAREN:
      case IDENT:
      case INTEGER:
      case DOT:
        enterOuterAlt(_localctx, 1);
        {
        setState(31);
        primitive();
        }
        break;
      case LPAREN:
        enterOuterAlt(_localctx, 2);
        {
        setState(32);
        match(LPAREN);
        setState(33);
        expression();
        setState(34);
        match(RPAREN);
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
      setState(40);
      switch (_input.LA(1)) {
      case IDENT:
        enterOuterAlt(_localctx, 1);
        {
        setState(38);
        match(IDENT);
        }
        break;
      case EOF:
      case PLUS:
      case TIMES:
      case RPAREN:
      case INTEGER:
      case DOT:
        enterOuterAlt(_localctx, 2);
        {
        setState(39);
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
      enterOuterAlt(_localctx, 1);
      {
      setState(43);
      _la = _input.LA(1);
      if (_la==INTEGER) {
        {
        setState(42);
        match(INTEGER);
        }
      }

      setState(51);
      _la = _input.LA(1);
      if (_la==DOT) {
        {
        setState(45);
        match(DOT);
        setState(47); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(46);
          match(INTEGER);
          }
          }
          setState(49); 
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
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\n8\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\3\3\7\3\25"+
      "\n\3\f\3\16\3\30\13\3\3\4\3\4\3\4\7\4\35\n\4\f\4\16\4 \13\4\3\5\3"+
      "\5\3\5\3\5\3\5\5\5\'\n\5\3\6\3\6\5\6+\n\6\3\7\5\7.\n\7\3\7\3\7\6\7"+
      "\62\n\7\r\7\16\7\63\5\7\66\n\7\3\7\2\2\b\2\4\6\b\n\f\2\28\2\16\3\2"+
      "\2\2\4\21\3\2\2\2\6\31\3\2\2\2\b&\3\2\2\2\n*\3\2\2\2\f-\3\2\2\2\16"+
      "\17\5\4\3\2\17\20\7\2\2\3\20\3\3\2\2\2\21\26\5\6\4\2\22\23\7\3\2\2"+
      "\23\25\5\6\4\2\24\22\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2"+
      "\2\2\27\5\3\2\2\2\30\26\3\2\2\2\31\36\5\b\5\2\32\33\7\4\2\2\33\35"+
      "\5\b\5\2\34\32\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37"+
      "\7\3\2\2\2 \36\3\2\2\2!\'\5\n\6\2\"#\7\5\2\2#$\5\4\3\2$%\7\6\2\2%"+
      "\'\3\2\2\2&!\3\2\2\2&\"\3\2\2\2\'\t\3\2\2\2(+\7\7\2\2)+\5\f\7\2*("+
      "\3\2\2\2*)\3\2\2\2+\13\3\2\2\2,.\7\b\2\2-,\3\2\2\2-.\3\2\2\2.\65\3"+
      "\2\2\2/\61\7\t\2\2\60\62\7\b\2\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61"+
      "\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65/\3\2\2\2\65\66\3\2\2\2\66"+
      "\r\3\2\2\2\t\26\36&*-\63\65";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}