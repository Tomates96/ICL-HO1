package ast;
import Utils.*;
import environment.*;
import values.*;

public interface ASTNode {

	IValue eval(Environment<IValue>  e) throws UndeclaredIdentifier, IdentifierDeclaredTwice, BadTypeException;
	void compile (CodeBlock c, CompileEnvironment<IValue> env) throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException;
}
