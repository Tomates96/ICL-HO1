package ast;
import environment.*;

public class ASTDef implements ASTNode{

	String id;
	ASTNode init;
	ASTNode body;
	
	public ASTDef(String id, ASTNode init, ASTNode body){
		this.id = id;
		this.init = init;
		this.body = body;
	}
	
	@Override
	public int eval(Environment e) throws UndeclaredIdentifier, IdentifierDeclaredTwice {
		int v1 = init.eval(e);
		e = e.beginScope();
		e.assoc(id, v1);
		int val = body.eval(e);
		e.endScope();
		return val;
	}

}
