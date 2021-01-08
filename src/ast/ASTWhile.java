package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTWhile implements ASTNode{

	ASTNode condition, expression;
	
	public ASTWhile(ASTNode condition, ASTNode expression){
		this.condition=condition;
		this.expression=expression;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue cond = condition.eval(e);
		if(cond instanceof boolValues){
			boolean b=((boolValues)cond).getValue();
			if(b){
				IValue v = expression.eval(e);
				return v;
			}
			else return new boolValues(false);		
		}
		throw new BadTypeException("Invalid condtion, expected boolean");
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		String start = c.emit_label();
		c.emit_comment("Compiling while cond");
		condition.compile(c, env);
		String endlabel = c.emit_while_condition();
		c.emit_comment("Compiling while body");
		expression.compile(c, env);
		c.emit_pop();
		c.emit_end_while(start,endlabel);
	}

}