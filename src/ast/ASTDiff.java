package ast;

import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTDiff implements ASTNode {

	private ASTNode left, right;

	public ASTDiff(ASTNode t1, ASTNode t2){
		left=t1;
		right=t2;
	}
	
	@Override
	public IValue eval(Environment<IValue> e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		IValue l=left.eval(e);
		IValue r=right.eval(e);
		if(l instanceof boolValues && r instanceof boolValues)
			return new boolValues(((boolValues)l).getValue() != ((boolValues)r).getValue());
		else if(l instanceof intValues && r instanceof intValues)
			return new boolValues(((intValues)l).getValue() != ((intValues)r).getValue());
		else if(l instanceof memCellValues && r instanceof memCellValues)
			return new boolValues(((memCellValues)l).get() != ((memCellValues)r).get());

		 else throw new BadTypeException("Invalid type, expected numbers");
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)
			throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException {
		left.compile(c, env);
		right.compile(c, env);
		c.emit_diff();
	}

}
