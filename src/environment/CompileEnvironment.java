package environment;

import java.util.ArrayList;
import Utils.VarCoord;

public class CompileEnvironment<X> extends Environment<X>{

	public static class Frame{
		String id;
		int offset;
		
		public Frame(String id, int offset) {
			this.id= id;
			this.offset=offset;
		}
	}
	
	private ArrayList<Frame> frames;
	private CompileEnvironment<X> up;
	private int framecount;
	
	public CompileEnvironment() {
		super();
		this.frames = new ArrayList<>(); 
	}
	
	public CompileEnvironment(CompileEnvironment<X> up) {
		this();
		this.up = up;
	}
	
	public CompileEnvironment<X> beginScope() {
		return new CompileEnvironment<X>(this);
	}

	
	public CompileEnvironment<X> endScope() {
		return up;
	}

	//new environment but same father
    public CompileEnvironment<X>  dupEnvironment(){
        return new CompileEnvironment<X> (this.up);
    }
	
	public int addFrame(String id) {
		frames.add(new Frame(id,framecount));
		framecount++;
		return framecount-1;
	}

	public VarCoord findFrame(String id) throws UndeclaredIdentifier {
		CompileEnvironment<X> current= this;
		int n_frames=0;
		while(current!=null) {
			for(Frame fra : current.frames) {
				if(fra.id.equals(id)) {
					return new VarCoord(n_frames, fra.offset);
				}
			}
			current = current.up;
			n_frames++;
		}
		throw new UndeclaredIdentifier();
	}

	

}
