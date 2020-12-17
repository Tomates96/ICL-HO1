package ast;
import Utils.*;
import environment.*;

public interface ASTNode {

	int eval(Environment e) throws UndeclaredIdentifier, IdentifierDeclaredTwice;
	void compile (CodeBlock c, CompileEnvironment env);
}
