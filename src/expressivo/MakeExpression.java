package expressivo;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.SumContext;

public class MakeExpression implements ExpressionListener {

	/**
	 * Get the result of this walk.
	 * @return the Expression object created from the AST walked
	 * 			over by the listener
	 */
	public Expression get() {
		throw new RuntimeException("not implemented");
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
	public void enterSum(SumContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitSum(SumContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterPrimitive(PrimitiveContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPrimitive(PrimitiveContext ctx) {
		// TODO Auto-generated method stub

	}

}
