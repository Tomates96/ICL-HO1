package ast;
import Utils.*;
import environment.*;
import values.IValue;

public class ASTId  implements ASTNode{

	String id;
	
	ASTId(String id){
		this.id = id;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
		
		return env.find(id);
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)  throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
		VarCoord vl = env.findFrame(id);
		c.emit_comment("Getting var "+ id + " jumps" + vl.frame);
		c.emit_idval(vl.frame,vl.offset);
	}


}
