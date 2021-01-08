package ast;
import Utils.CodeBlock;
import environment.*;
import values.*;

public class ASTNum implements ASTNode {

		public int num;
   
        public  IValue eval(Environment<IValue> env) throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
    		return new intValues(num);
    	}
        
        public ASTNum(int n)
        {
        	num = n;
        }

		@Override
		public void compile(CodeBlock c, CompileEnvironment<IValue> env)  throws UndeclaredIdentifier, IdentifierDeclaredTwice,  BadTypeException{
			c.emit("sipush "+num);
		}		

}

