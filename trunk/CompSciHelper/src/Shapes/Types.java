package Shapes;

public class Types {
	public enum type {
	    BubbleSort, QuakerSort,
	    QuickSort
	}
	
	public type whatType;
	
	public type getType(int x){
		return type.values()[x];
	}
	public void setType(String s){
		if(s == "BubbleSort")
			whatType = type.BubbleSort;
		else if (s == "QuakerSort")
			whatType = type.QuakerSort;
		else if(s == "QuickSort")
			whatType = type.QuickSort;
	}
	public void setType(int i){
		whatType = type.values()[i];
	}
}
