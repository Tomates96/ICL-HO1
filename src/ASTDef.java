
public class ASTDef implements ASTNode{

	String id;
	ASTNode init;
	ASTNode body;
	
	ASTDef(String id){
		this.id = id;
	}
	
	@Override
	public int eval(Environment e) {
		// To do 
		return 0;
	}

}
