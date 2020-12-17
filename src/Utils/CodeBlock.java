package Utils;

import java.io.PrintStream;
import java.util.*;

public class CodeBlock {

	
	public ArrayList<String> code;
	
	public CodeBlock(){
		code = new ArrayList<>(100);
	}
	
	public void emit(String bytecode) {
		code.add(bytecode);
	}
	
	public String gensysm() {
		return null;
	}
	
	public void dump(PrintStream f) {
		/*f.println(".class public Demo");
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
		f.println("");*/
		for(String line: code) {
			f.println("       "+line);
		}
		
	}
}
