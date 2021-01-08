package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTIf implements ASTNode{
	
	ASTNode ifs, thens, elss;

	public ASTIf(ASTNode cond, ASTNode t, ASTNode e){
		ifs=cond; //if statement
		thens=t; //then statement
		elss=e;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue v = ifs.eval(e);
		if( v instanceof boolValues) {
			boolean value = ((boolValues)v).getValue();
			if(value) return thens.eval(e);
			else return elss.eval(e);
		}
		throw new BadTypeException("Invalid type, expected boolean");
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		ifs.compile(c, env);
		String label = c.emit_if_condition();
		label = c.emit_else(label);
		thens.compile(c, env);
		String end = c.emit_end();
		c.emit_then(label,end);
		elss.compile(c, env);
		c.finishIf(end);
	}

}

