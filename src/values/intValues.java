package values;

public class intValues implements IValue {
	private int value;

	public intValues(int value){
		this.value=value;
	}

	public int getValue(){
		return value;
	}

	public void setValue(int value){
		this.value=value;
	}

	 @Override
	public String toString(){
		return String.valueOf(value);
	}
}