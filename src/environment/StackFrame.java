package environment;

import java.io.*;
import Utils.*;

public class StackFrame {
        public int number, nLets;
        public StackFrame parent;
        static int fnumber = 1;
        public boolean declaration;
        //IType[] types;

       public StackFrame(int nLets, StackFrame parent){
            this.number=fnumber++;
            this.nLets=nLets;
            this.parent=parent;
            this.declaration=true;
            //this.types=new IType[nLets];
        }

        /*public void setFieldType(int field, IType type){
            this.types[field]= type;
        }*/
        public void finishDeclar(){
            this.declaration = false;
        }
        
        public void dump(){
            FileOutputStream outputStream;
            OutputStreamWriter writer;
            try{
                outputStream = new FileOutputStream("frame_"+this.number+".j");
                writer = new OutputStreamWriter(outputStream);

                //Writting header
                writer.write(".class frame_"+this.number+"\n"
                    +".super java/lang/Object\n");
                //have we got parent frame?
                if(this.parent!=null)
                    writer.write(".field public sl Lframe_"+this.parent.number+";\n");

                //field declarations
                for (int i = 0; i < this.nLets; i++) {
                    //String type = CodeBlock.getTypeLabel(types[i]);
                    String locName = String.format("v",i);
                    writer.write(".field public "+locName+" "+/*type+*/"\n");
                }

                //writting footer
                writer.write(".method public <init>()V\naload_0\ninvokespecial java/lang/Object/<init>()V\n"
                    + "return\n.end method");
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();

            }
        }
}
	
