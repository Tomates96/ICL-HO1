package ast;
import Utils.CodeBlock;
import environment.*;

public class ASTNum implements ASTNode {

		public int id;

        public int eval(Environment e) throws UndeclaredIdentifier { 
        	return e.find(String.valueOf(id)); 
        	}

        public ASTNum(int n)
        {
        	id = n;
        }

		@Override
		public void compile(CodeBlock c, CompileEnvironment env) {
			c.emit("sipush");
		}

		

}

