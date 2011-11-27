package hashing;

public class Probing {
	public static int LINEAR_PROBING = 1;
	public static int QUADRATIC_PROBING = 2;
	
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
	
	public void incProbe(){
		k += 1;
	}
	public void reset(){
		k = 0;
	}
	
	public int returnType(){
		return type;
	}
	
	public int getProbeSpot(){
		if(type == LINEAR_PROBING){
			linearProbe();
		}
		else if(type == QUADRATIC_PROBING){
			quadradicProb();
		}
		return newSpot;
	}
	
	public void setProbeSpot(int spot){
		probeSpot = spot;
	}
}
