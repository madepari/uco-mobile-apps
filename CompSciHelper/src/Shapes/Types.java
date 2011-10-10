package Shapes;

public class Types {
	public enum type {
	    BubbleSort, QuakerSort,
	    BinaryTree
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
		else if(s == "BinaryTree")
			whatType = type.BinaryTree;
	}
	public void setType(int i){
		whatType = type.values()[i];
	}
}
