package ast;

import Utils.CodeBlock;
import environment.*;
import values.IValue;
import values.memCellValues;

public class ASTAssign implements ASTNode{

	private ASTNode left, right;
	
	public ASTAssign(ASTNode t1, ASTNode t2){
		left=t1;
		right=t2;
	}
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue l = left.eval(e);
		IValue r = right.eval(e);
		if(l instanceof memCellValues) {
			((memCellValues)l).set(r);
			return r;		
		}
		throw new BadTypeException("Invalid type, expected reference");
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		c.emit_comment("Assignment");
		left.compile(c,env);
		c.emit_dup();
		right.compile(c, env);
	}

}

