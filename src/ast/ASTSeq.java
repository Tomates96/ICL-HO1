package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTSeq implements ASTNode{

	private ASTNode left, right;
	
	public ASTSeq(ASTNode t1, ASTNode t2){
		left=t1;
		right=t2;
	}
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue l =left.eval(e);
		IValue r =right.eval(e);
		return r;
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		left.compile(c, env);
		c.emit_pop();
		right.compile(c,env);
		
	}

}

