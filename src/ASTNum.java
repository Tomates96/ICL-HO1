public class ASTNum implements ASTNode {

String id;

        public int eval(Environment e) { return e.find(id); }

        public ASTNum(String n)
        {
	   id = n;
        }

}

