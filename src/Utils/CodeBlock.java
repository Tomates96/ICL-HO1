package Utils;

import java.io.PrintStream;
import java.util.*;
import environment.*;

public class CodeBlock {

	private static final boolean DEBUG = true;
	
	public ArrayList<String> code;
	private LinkedList<StackFrame> frames;
    private Stack<StackFrame> stack;
	
	public CodeBlock(){
		code = new ArrayList<>(100);
	}
	
	public void emit(String bytecode) {
		code.add(bytecode);
	}
	
	public void emit_dup() {
		code.add("dup");
	}
	
	public void emit_loadReference(int i) {
		code.add("aload_"+i);
	}
	
	public void emit_comment(String comment){
	        if(DEBUG)
	            code.add(";"+comment);
	 }
	
	public void emit_idval(int jumps, int offset/*, IType type*/){
        StackFrame frame = getCurrentFrame();
        for(; jumps > 1; jumps --){
            code.add("getfield frame_"+frame.number+"/SL Lframe_"+frame.parent.number+";");
            frame = frame.parent;
        }
       // String type_name = CodeBlock.getTypeLabel(type);
       code.add("getfield frame_"+ frame.number+"/loc_0"+offset/*+type_name*/);
    }
	
	public  void emit_store(int frame_n, int offset){
        code.add("putfield frame_"+ frame_n+"/"+String.format("v",offset));
    }
	
	
	public void emit_push_frame(int frame_n){
        code.add("astore 0");
        StackFrame frame = getFrame(frame_n);
        frame.finishDeclar();
        this.stack.push(frame);
    }

     public void emit_pop_frame(int frame_n){
        stack.pop();
        StackFrame frame = getFrame(frame_n);
        if(frame != null && frame.parent != null){
            code.add("aload 0");
            code.add("checkcast frame_"+frame.number);
            code.add("getfield frame_"+frame.number+"/sl Lframe_"+frame.parent.number+";");
            code.add("astore 0");
        }
        else{
            code.add("aconst_null");
            code.add("astore 0");
        }
    }
	
	
	public void init_frame(int frame_n){
        StackFrame frame = getFrame(frame_n);
        if(frame.parent != null){
            emit_dup();
            code.add("aload_0");
            code.add("putfield frame_"+frame_n+"/sl Lframe_"+frame.parent.number+";");
        }
    }
	
	public int createFrame(int size, int parent){
        StackFrame frame = this.getFrame(parent);
        frame = new StackFrame(size, frame);
        frames.add(frame);
        code.add("new frame_"+frame.number);
        emit_dup();
        code.add("invokespecial frame_"+frame.number+"/<init>()V");
        return frame.number;
    }

    public int createFrame(int size){
        StackFrame frame = this.getCurrentFrame();
        if (frame == null)
            frame = new StackFrame(size,null);  //FRAMES SEM PAI
        else if(!frame.declaration)
            frame = new StackFrame(size,this.getCurrentFrame());
        else frame = new StackFrame(size,this.getCurrentFrame().parent);

        frames.add(frame);
        code.add("new frame_"+frame.number);
        emit_dup();
        code.add("invokespecial frame_"+frame.number+"/<init>()V");
        return frame.number;
    }
	
    public int getParentFrame(){
        try{
            StackFrame current = stack.peek();
            if(current.parent != null)
                return current.parent.number;
            return -1;

        }catch(EmptyStackException e){
            e.printStackTrace();
            return -1;
        }
    }

    public StackFrame getCurrentFrame() {
        try{
            return stack.peek();
        }catch(EmptyStackException e){
            return null;
        }
    }

    public StackFrame getFrame(int frame_n){
        for (int i = 0; i <frames.size() ; i++)
            if(frames.get(i).number == frame_n)
                return frames.get(i);
        return null;
    }
	
	public void dump(PrintStream f) {
		f.println(".class public Demo");
		f.println(".super java/lang/Object");
		f.println("");
		f.println(";");
		f.println("; standard initializer");
		f.println(".method public <init>()V");
		f.println("   aload_0");
		f.println("   invokenonvirtual java/lang/Object/<init>()V");
		f.println("   return");
		f.println(".end method");
		f.println("");
		f.println(".method public static main([Ljava/lang/String;)V");
		f.println("       ; set limits used by this method");
	    f.println("       .limit locals  2");
		f.println("       .limit stack 256");
		f.println("");
		f.println("       ; setup local variables:");
		f.println("");
		f.println("       ;    1 - the PrintStream object held in java.lang.System.out");
		f.println("       getstatic java/lang/System/out Ljava/io/PrintStream;");
		f.println("");
		f.println("       ; place your bytecodes here");
		f.println("       ; START");
		f.println("");
		for(String line: code) {
			f.println("       "+line);
		}
		f.println("       ; END");
        f.println("");
        f.println("       ; convert to String;");
        f.println("       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        f.println("       ; call println ");
        f.println("       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        f.println("");
        f.println("       return");
        f.println("");
        f.println(".end method");		
	}
	
	
}
