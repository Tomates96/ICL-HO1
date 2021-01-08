package values;

public class memCellValues implements IValue{
	IValue v;
	
	public memCellValues(IValue v0) 
	{ v = v0; } 
	
	public IValue get() 
	{ return v;} 
	
	public void set(IValue v0)
	{ v = v0;}
	
	 @Override
		public String toString(){
			return String.valueOf(v);
		}
}
