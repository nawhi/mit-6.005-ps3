/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
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

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition:
	// 
	// Expression ::= 
	// 		Primitive(p: Primitive) + BinOp(left: Primitive, right: Primitive)
	// Primitive ::= 
	//		Number(n: int) + Number(n: float) + Variable(v: String)
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    static Expression parse(String input) {
        CharStream stream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        
        parser.reportErrorsAsExceptions();
        lexer.reportErrorsAsExceptions();


        ParseTree tree;
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionGenerator listener = new ExpressionGenerator();

        try {
            tree = parser.root();
            walker.walk(listener, tree);
        } catch (ParseCancellationException ex) {
            throw new IllegalArgumentException(formatParseError(ex));
        }

        return listener.get();
    }

    static String formatParseError(ParseCancellationException ex) {
        String reason = ex.getMessage();
        String msg = "Syntax error in expression";
        if (reason != null) {
            msg += ": " + reason;
        }
        return msg;
    }

    static String differentiate(String rawExpression, String rawVariable) {
        Expression expression = parse(rawExpression);
        Variable variable = parseVariable(rawVariable);
        return expression.differentiate(variable).simplified().toString();
    }

    static Variable parseVariable(String rawVariable) {
        Expression ex = parse(rawVariable);
        if (!(ex instanceof Variable))
            throw new IllegalArgumentException("Can't differentiate with respect to '" + ex + "' because it's not a variable");
        return (Variable) ex;
    }

    /**
     * Find whether this Expression will have precedence over another.
     * If not, brackets will be needed to combine the two expressions in
     * a human-readable format (eg a string).
     *
     * @param other the second Expression
     * @return true if this Expression can be combined with other without
     *         needing brackets, false otherwise
     */
    boolean precedes(Expression other);

    Expression simplified();

    /**
     * @param variable Variable to differentiate by
     * @return differential of this expression with respect to variable
     */
    Expression differentiate(Variable variable);

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override
    String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    int hashCode();
    
    
}
