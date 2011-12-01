package hashing;

public class Probing {
	public static int LINEAR_PROBING = 1;
	public static int QUADRATIC_PROBING = 2;
	public static int CHAINING_PROBING = 3;
	
	private int type;
	private int max;
	private int probeSpot;
	private int newSpot;
	private int k = 0;
	
	public Probing(int type, int max){
		this.type = type;
		this.max = max;
	}

	public void quadradicProb(){
		newSpot = (probeSpot + (k*k)) % max;
	}
	
	public void linearProbe(){
		newSpot = (probeSpot + k) % max;
	}
	
	public void chainingProbe(){
		
	}
	
	public void incProbe(){
		k += 1;
	}
	
	public void reset(){
		k = 0;
	}
	
	public int getType(){
		return type;
	}
	
	public int getProbeSpot(){
		if(type == LINEAR_PROBING){
			linearProbe();
		}
		else if(type == QUADRATIC_PROBING){
			quadradicProb();
		}
		else if(type == CHAINING_PROBING){
			newSpot = probeSpot;
		}
		return newSpot;
	}
	
	public void setProbeSpot(int spot){
		probeSpot = spot;
	}
}
