package expressivo.expressions;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

class ParseExpression {

    Expression parse(String input) {
        CharStream stream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);

        parser.reportErrorsAsExceptions();
        lexer.reportErrorsAsExceptions();

        return walkParseTree(parser);
    }

    private Expression walkParseTree(ExpressionParser parser) {
        ParseTree tree;
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionGenerator expressionGenerator = new ExpressionGenerator();

        try {
            tree = parser.root();
            walker.walk(expressionGenerator, tree);
        } catch (ParseCancellationException ex) {
            throw new IllegalArgumentException(formatParseError(ex));
        }

        return expressionGenerator.finalExpression();
    }

    private static String formatParseError(ParseCancellationException ex) {
        String reason = ex.getMessage();
        String msg = "Syntax error in expression";
        if (reason != null) {
            msg += ": " + reason;
        }
        return msg;
    }
}
