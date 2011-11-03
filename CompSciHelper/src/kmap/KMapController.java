package kmap;

public class KMapController {

	public static final int VARIABLE_3 = 0;
	public static final int VARIABLE_4 = 1;
	public static final int VARIABLE_5 = 2;
	private int KMapSize;

	public KMapController(int KMapSize) {
		this.KMapSize = KMapSize;
	}

	private String topDisplay[] = { "   ", "00", "01", "11", "10" };
	private String leftDisplay[][] = { { "0", "1" }, { "00", "01", "11", "10" } };
	private int tableLayout[] = { 0, 1, 3, 2, 4, 5, 7, 6, 12, 13, 15, 14, 8, 9,
			11, 10 };

	public String[] getTopDisplay() {
		return topDisplay;
	}

	public String[] getLeftDisplay() {
		if (KMapSize == VARIABLE_5)
			return leftDisplay[VARIABLE_4];
		else
			return leftDisplay[KMapSize];
	}

	public int[] getTableLayout() {
			return tableLayout;
	}
	
	public int getKMapSize(){
		return KMapSize;
	}
}
