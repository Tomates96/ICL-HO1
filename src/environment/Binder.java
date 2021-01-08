package environment;

import ast.ASTNode;

public class Binder {

	String id;
	private ASTNode expr;
	
	public Binder(String id, ASTNode expr) {
		this.id = id;
		this.expr = expr;
		
	}
	
	public String getID() {return id;}
	
	public ASTNode getExp() {return expr;}
	
	public boolean equals(Object obj) {
		if(this == obj)return true;
		if(obj == null)return false;
		if(getClass() != obj.getClass()) return false;
		Binder other =   (Binder) obj;
		if(expr == null) {
			if(other.expr != null) {return false;}
		}		
		if(id == null) {
			if(other.id!=null)return false;
		}
		else if (!id.equals(other.id)) {return false;}
		return true;
	}
	
}
