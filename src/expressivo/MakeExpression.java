package expressivo;

import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.NumberContext;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.RootContext;

public class MakeExpression implements ExpressionListener {
	private Stack<Expression> stack = new Stack<>();
	/**
	 * Get the result of this walk.
	 * @return the Expression object created from the AST walked
	 * 			over by the listener
	 */
	public Expression get() {
		return stack.get(0);
	}

	
	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRoot(RootContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRoot(RootContext ctx) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void enterExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void exitExpression(ExpressionContext ctx) {
		/*
		 * Exiting the primitive PLUS primitive rule
		 * Make a Sum from the 2 entries on the stack
		 */
		assert stack.size() >= 2;
		
		Expression rval = stack.pop();
		Expression lval = stack.pop();
		stack.push(new Sum(lval, rval));
	}


	@Override
	public void enterNumber(NumberContext ctx) {
		// nothing to do
	}


	@Override
	public void exitNumber(NumberContext ctx) {
		stack.push(new Numeric(ctx.getText()));
		
	}


	@Override
	public void enterPrimitive(PrimitiveContext ctx) {
		// TODO Auto-generated method stub
	}


	@Override
	public void exitPrimitive(PrimitiveContext ctx) {
		if (ctx.IDENT() != null) {
			// it's a variable: add it to the stack
			stack.push(new Variable(ctx.IDENT().getText()));
		} else {
			// will have been handled in exitNumber()
		}
	}

}
