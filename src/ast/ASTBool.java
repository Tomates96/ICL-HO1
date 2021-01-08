package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTBool implements ASTNode{

	
	boolean value;

	public ASTBool(boolean b){ value=b; }

	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		return new boolValues(value);
	}
	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		int i = value ? 1 : 0;
		c.emit("sipush "+i);
		
	}

}

