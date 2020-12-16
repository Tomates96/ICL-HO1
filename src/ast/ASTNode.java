package ast;
import environment.*;

public interface ASTNode {

	int eval(Environment e) throws UndeclaredIdentifier, IdentifierDeclaredTwice;
	
}
