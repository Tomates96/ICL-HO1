package ast;
import Utils.CodeBlock;
import environment.*;
import values.IValue;
import values.intValues;
import values.memCellValues;

public class ASTDiv implements ASTNode {

	ASTNode lhs, rhs;

	
	public ASTDiv(ASTNode l, ASTNode r)
    {
    	lhs = l; rhs = r;
    }
	
    public IValue eval(Environment<IValue> env) throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
    	IValue l=lhs.eval(env);
		IValue r=rhs.eval(env);
		if(l instanceof intValues && r instanceof intValues)
			return new intValues(((intValues)l).getValue() / ((intValues)r).getValue());
		
		else if(l instanceof memCellValues && r instanceof intValues){
			IValue int_l = ((memCellValues)l).get();
				if( int_l instanceof  intValues)
					return new intValues(((intValues)int_l).getValue() / ((intValues)r).getValue());
		}
		else if(l instanceof intValues && r instanceof memCellValues) {
			IValue int_r = ((memCellValues) r).get();
			if (int_r instanceof intValues)
				return new intValues(((intValues) l).getValue() / ((intValues) int_r).getValue());

		}
		throw new BadTypeException("Invalid type, expected num");
    }

    
	@Override
	public void compile(CodeBlock c, CompileEnvironment<IValue> env)  throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
		lhs.compile(c, env);
		rhs.compile(c, env);
		c.emit("idiv");
	}

	
}