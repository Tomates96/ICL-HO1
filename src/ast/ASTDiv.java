package ast;
import Utils.CodeBlock;
import environment.*;

public class ASTDiv implements ASTNode {

	ASTNode lhs, rhs;

    public int eval(Environment e) throws UndeclaredIdentifier, IdentifierDeclaredTwice
    { 
    	int v1 = lhs.eval(e);
    	int v2 = rhs.eval(e);
    return v1/v2; 
}

    public ASTDiv(ASTNode l, ASTNode r)
    {
    	lhs = l; rhs = r;
    }

	@Override
	public void compile(CodeBlock c, CompileEnvironment env) {
		lhs.compile(c, env);
		rhs.compile(c, env);
		c.emit("idiv");
	}

	
}