package kmap;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KMapController {

	// Only Handles 3, 4, & 5 variables
	public static final int VARIABLE_3 = 0;
	public static final int VARIABLE_4 = 1;
	public static final int VARIABLE_5 = 2;
	private static final String varibles[][] = { { "X", "Y", "Z" },
			{ "W", "X", "Y", "Z" }, { "V", "W", "X", "Y", "Z" } };
	private String topDisplay[] = { "   ", "00", "01", "11", "10" };
	private String leftDisplay[][] = { { "0", "1" }, { "00", "01", "11", "10" } };
	private int KMapSize;
	private int tableLayout[] = { 0, 1, 3, 2, 4, 5, 7, 6, 12, 13, 15, 14, 8, 9,
			11, 10 };
	private boolean tableHolder[];

	// Constructor
	public KMapController(int KMapSize) {
		this.KMapSize = KMapSize;
		if (KMapSize == VARIABLE_3)
			tableHolder = new boolean[8];
		else if (KMapSize == VARIABLE_4)
			tableHolder = new boolean[16];
		else if (KMapSize == VARIABLE_5)
			tableHolder = new boolean[32];
	}

	// returns top display array
	public String[] getTopDisplay() {
		return topDisplay;
	}

	// based on the size returns the left string array
	public String[] getLeftDisplay() {
		if (KMapSize == VARIABLE_5)
			return leftDisplay[VARIABLE_4];
		else
			return leftDisplay[KMapSize];
	}

	// returns minterm latout
	public int[] getTableLayout() {
		return tableLayout;
	}

	// returns the size of the table
	public int getKMapSize() {
		return KMapSize;
	}

	public String getStringVariableAtLocation(int mintermSpot) {
		String[] vars = getVariableLanguage();
		String binary = getBinarySpot(mintermSpot);
		String variableString = "";
		for (int x = 0; x < binary.length(); x++) {
			if (binary.charAt(x) == '0')
				variableString += vars[x] + "'";
			else
				variableString += vars[x];
		}
		return variableString;
	}

	public boolean getVariableAtLocation(int mintermSpot) {
		return tableHolder[mintermSpot];
	}

	public void setVariableAtLocation(int mintermSpot, boolean checked) {
		int row = getRowSpot(mintermSpot) * 4;
		int col = getColumnSpot(mintermSpot);
		if (mintermSpot <= 15) {
			tableHolder[(row + col)] = checked;
		} else
			tableHolder[(row + col + 16)] = checked;
	}

	public String[] getVariableLanguage() {
		return varibles[KMapSize];
	}

	public String getBinarySpot(int mintermSpot) {
		String binary = "";
		if (KMapSize == VARIABLE_5) {
			if (mintermSpot <= 15)
				binary = 0 + "";
			else
				binary = 1 + "";
			binary += getLeftDisplay()[getRowSpot(mintermSpot)];
			binary += getTopDisplay()[getColumnSpot(mintermSpot) + 1];
			return binary;
		} else {
			binary = getLeftDisplay()[getRowSpot(mintermSpot)];
			binary += getTopDisplay()[getColumnSpot(mintermSpot) + 1];
			return binary;
		}

	}

	private int getColumnSpot(int mintermSpot) {
		switch (mintermSpot) {
		case 0:
		case 4:
		case 12:
		case 8:
		case 16:
		case 20:
		case 28:
		case 24:
			return 0;
		case 1:
		case 5:
		case 13:
		case 9:
		case 17:
		case 21:
		case 29:
		case 25:
			return 1;
		case 3:
		case 7:
		case 15:
		case 11:
		case 19:
		case 23:
		case 31:
		case 27:
			return 2;
		case 2:
		case 6:
		case 14:
		case 10:
		case 18:
		case 22:
		case 30:
		case 26:
			return 3;
		}
		return -1;
	}

	private int getRowSpot(int mintermSpot) {
		switch (mintermSpot) {
		case 0:
		case 1:
		case 3:
		case 2:
		case 16:
		case 17:
		case 19:
		case 18:
			return 0;
		case 4:
		case 5:
		case 6:
		case 7:
		case 20:
		case 21:
		case 23:
		case 22:
			return 1;
		case 12:
		case 13:
		case 15:
		case 14:
		case 28:
		case 29:
		case 31:
		case 30:
			return 2;
		case 8:
		case 9:
		case 11:
		case 10:
		case 24:
		case 25:
		case 27:
		case 26:
			return 3;
		}
		return -1;
	}

	public String getEquation() {
		String equation = "";
		for (int x = 0; x < tableHolder.length; x++) {
			if (tableHolder[x]) {
				if (!equation.equals(""))
					equation += " + ";
				if (x <= 15)
					equation += getStringVariableAtLocation(tableLayout[x]);
				else
					equation += getStringVariableAtLocation(tableLayout[x - 16] + 16);
			}
		}
		return equation;

	}

	public String getReducedEquation() {
		ArrayList<Term> formula = new ArrayList<Term>();
		Formula f;
		String holder;

		byte[] temp;
		for (int x = 0; x < tableHolder.length; x++) {
			if (tableHolder[x]) {

				if (x <= 15)
					holder = getBinarySpot(tableLayout[x]);
				else
					holder = getBinarySpot(tableLayout[x - 16] + 16);
				temp = new byte[holder.length()];

				for (int y = 0; y < holder.length(); y++) {
					temp[y] = Byte.parseByte("" + holder.charAt(y));
				}
				formula.add(new Term(temp));
			}
		}

		f = new Formula(formula);
		f.reduceToPrimeImplicants();
		f.reducePrimeImplicantsToSubset();
		return reducedEquation(f.toString());
	}

	private String reducedEquation(String eq) {
		String output = "";
		int counter = 0;
		for (int x = 0; x < eq.length() - 1; x++) {
			if (eq.charAt(x) != 'X') {
				if (eq.charAt(x) == ' ') {
					output += " + ";
					counter = -1;
				} else {
					output += getVariableLanguage()[counter];
					if (eq.charAt(x) == '0')
						output += "'";

				}
			}
			counter += 1;
		}
		return output;

	}
}
