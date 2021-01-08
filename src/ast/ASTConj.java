package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTConj implements ASTNode{
	
	ASTNode left,right;
	
	public ASTConj(ASTNode t1, ASTNode t2){
		left=t1;
		right=t2;	
	}
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue l = left.eval(e);
		IValue r = right.eval(e);
		if(l instanceof boolValues && r instanceof boolValues)
			return new boolValues(((boolValues)l).getValue() && ((boolValues)r).getValue());
		throw new BadTypeException("Invalid type, expected boolean");
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		left.compile(c,env);
		right.compile(c, env);
		c.emit_and();
	}

}
