package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTNot implements ASTNode{

	ASTNode val;
	
	public ASTNot(ASTNode val){
		this.val=val;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue value= val.eval(e);
		if( value instanceof boolValues)
			return new boolValues(!((boolValues)value).getValue());
		throw new BadTypeException("Invalid type, expected boolean");
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		val.compile(c, env);
		c.emit_not();
	}

}

