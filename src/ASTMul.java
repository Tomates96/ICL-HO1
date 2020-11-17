public class ASTMul implements ASTNode {

ASTNode lhs, rhs;

	public ASTMul(ASTNode l, ASTNode r)
	{
		lhs = l; rhs = r;
	}
	public int eval(Environment e) throws UndeclaredIdentifier, IdentifierDeclaredTwice
    { 
		
		int v1 = lhs.eval(e);
		int v2 = rhs.eval(e);
        return v1+v2; 
	}
	
    
}

