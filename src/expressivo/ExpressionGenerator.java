package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser.*;

public class ExpressionGenerator implements ExpressionListener {

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
	public void exitTerm(TermContext ctx) {
		// atom TIMES atom
		List<AtomContext> atomContexts = ctx.atom();
		assert stack.size() >= atomContexts.size();
		
		Expression product = stack.pop(); // 0th child
		for (int i = 1; i < atomContexts.size(); ++i) {
			product = new Product(stack.pop(), product);
		}
		stack.push(product);
	}
	
	@Override
	public void exitNumber(NumberContext ctx) {
		stack.push(new Numeric(ctx.getText()));
	}
	
	@Override
	public void exitPrimitive(PrimitiveContext ctx) {
		if (ctx.IDENT() != null) {
			stack.push(new Variable(ctx.IDENT().getText()));
		}
	}
	
	@Override
	public void exitExpression(ExpressionContext ctx) {
		// term PLUS term
		List<TermContext> termContexts = ctx.term();
		assert stack.size() >= termContexts.size();
		
		Expression sum = stack.pop(); // 0th child
		for (int i = 1; i < termContexts.size(); ++i) {
			sum = new Sum(stack.pop(), sum);
		}
		stack.push(sum);
	}

	@Override public void enterEveryRule(ParserRuleContext arg0) {}
	@Override public void exitEveryRule(ParserRuleContext arg0) {}
	@Override public void enterRoot(RootContext ctx) {}
	@Override public void exitRoot(RootContext ctx) {}
	@Override public void enterExpression(ExpressionContext ctx) {}
	@Override public void enterTerm(TermContext ctx) {}
	@Override public void enterAtom(AtomContext ctx) {}
	@Override public void exitAtom(AtomContext ctx) {}
	@Override public void enterPrimitive(PrimitiveContext ctx) {}
	@Override public void enterNumber(NumberContext ctx) {}
	@Override public void visitErrorNode(ErrorNode arg0) {}
	@Override public void visitTerminal(TerminalNode arg0) {}
	
	

}
