package values;

public class boolValues implements IValue {
	private boolean value;

	public boolValues(boolean value){
		this.value=value;
	}

	public boolean getValue(){
		return value;
	}

	public void setValue(boolean value){
		this.value=value;
	}

	 @Override
	public String toString(){
		return String.valueOf(value);
	}
}