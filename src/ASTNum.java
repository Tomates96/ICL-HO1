public class ASTNum implements ASTNode {

int id;

        public int eval(Environment e) throws UndeclaredIdentifier { 
        	return e.find(String.valueOf(id)); 
        	}

        public ASTNum(int n)
        {
	   id = n;
        }

		

}

