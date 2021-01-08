package ast;
import java.util.List;

import Utils.CodeBlock;
import environment.*;
import values.IValue;

public class ASTDef implements ASTNode{

	private List<Binder> bindings;
	ASTNode body;
	
	public ASTDef(List<Binder> binders, ASTNode body){
		bindings = binders;
		this.body = body;
	}
	
	@Override
	public IValue eval(Environment<IValue> env) throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
		IValue value;
		Environment<IValue> local = env.beginScope();
		for(Binder b: bindings) {
			IValue valueID = b.getExp().eval(local);
			local.assoc(b.getID(), valueID);
		}
		value = body.eval(local);
		local.endScope();
		return value;
	}

	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)  throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
		int frame_n = c.createFrame(bindings.size());
		c.init_frame(frame_n);
		CompileEnvironment<IValue> new_env;
		for (int i = 0; i < bindings.size() ; i++) {
			Binder let = bindings.get(i);
			String id = let.getID();
			int offset = env.addFrame(id);
			c.emit_comment("Load variable frame_"+frame_n+"/"+id);
			c.emit_dup();
			let.getExp().compile(c,env.dupEnvironment());
			//c.addTypeToFrame(let.getExpression().getType(),i,frame_n);
			c.emit_store(frame_n,offset/*,let.getExp().getType()*/);
		}
		new_env = env.beginScope();
		c.emit_push_frame(frame_n);
		body.compile(c,new_env);
		c.emit_comment("Finish frame"+frame_n);
		c.emit_pop_frame(frame_n);
		new_env.endScope();
		
	}

}
