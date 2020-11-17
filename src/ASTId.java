
public class ASTId  implements ASTNode{

	String id;
	
	ASTId(String id){
		this.id = id;
	}

	@Override
	public int eval(Environment e) throws UndeclaredIdentifier {
		
		return e.find(id);
	}
	

}
