package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser.BinopContext;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.NumberContext;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.ProductContext;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.SumContext;

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
		// nothing to do
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
			// handled in exitNumber()
		}
	}


	@Override
	public void enterSum(SumContext ctx) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exitSum(SumContext ctx) {
		// List of all the primitives in this context
		List<PrimitiveContext> primitives = ctx.primitive();
		assert stack.size() >= primitives.size();
		
		/*
		 * Stack will be a list of the two or more primitives
		 * (newest first) which we need to incorporate
		 * into our chained sum
		 */
		Expression sum = stack.pop(); // 0th child
		for (int i = 1; i < primitives.size(); ++i) {
			sum = new Sum(stack.pop(), sum);
		}
		stack.push(sum);
	}


	@Override
	public void enterProduct(ProductContext ctx) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exitProduct(ProductContext ctx) {
		// List of all the primitive contexts in this context
		List<PrimitiveContext> primitives = ctx.primitive();
		assert stack.size() >= primitives.size();
		
		/*
		 * Stack will be a list of the two or more primitives
		 * (newest first) which we need to incorporate
		 * into our chained product
		 */
		Expression prod = stack.pop(); // 0th child
		for (int i = 1; i < primitives.size(); ++i) {
			prod = new Product(stack.pop(), prod);
		}
		stack.push(prod);
	}


	@Override
	public void enterBinop(BinopContext ctx) {
		// nothing to do
	}


	@Override
	public void exitBinop(BinopContext ctx) {
		// nothing to do
	}

}
