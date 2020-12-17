package ast;
import Utils.CodeBlock;
import environment.*;

public class ASTPlus implements ASTNode {

ASTNode lhs, rhs;

	public ASTPlus(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}
	public int eval(Environment e) throws UndeclaredIdentifier, IdentifierDeclaredTwice
    { 
		
		int v1 = lhs.eval(e);
		int v2 = rhs.eval(e);
        return v1+v2; 
	}
	@Override
	public void compile(CodeBlock c, CompileEnvironment env) {
		lhs.compile(c, env);
		rhs.compile(c, env);
		c.emit("iadd");
	}
	
    
}

