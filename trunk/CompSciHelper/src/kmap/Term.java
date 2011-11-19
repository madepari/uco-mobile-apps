package kmap;

import java.util.*;

public class Term {
	private byte[] varVals;
	private List<Term> originalTermList;

	public static final byte DontCare = 2;

	public Term(byte[] varVals) {
		this.varVals = varVals;
	}

	public int getNumVars() {
		return varVals.length;
	}

	public Term combine(Term term) {
		int diffVarNum = -1; // The position where they differ
		for (int i = 0; i < varVals.length; i++) {
			if (this.varVals[i] != term.varVals[i]) {
				if (diffVarNum == -1) {
					diffVarNum = i;
				} else {
					// They're different in at least two places
					return null;
				}
			}
		}
		if (diffVarNum == -1) {
			// They're identical
			return null;
		}
		byte[] resultVars = varVals.clone();
		resultVars[diffVarNum] = DontCare;
		return new Term(resultVars);
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < varVals.length; i++) {
			if (varVals[i] == DontCare)
				result += "X";
			else
				result += varVals[i];

		}
		return result;
	}

	public int countValues(byte value) {
		int result = 0;
		for (int i = 0; i < varVals.length; i++) {
			if (varVals[i] == value) {
				result++;
			}
		}
		return result;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o == null || !getClass().equals(o.getClass())) {
			return false;
		} else {
			Term rhs = (Term) o;
			return Arrays.equals(this.varVals, rhs.varVals);
		}
	}

	boolean implies(Term term) {
		for (int i = 0; i < varVals.length; i++) {
			if (this.varVals[i] != DontCare
					&& this.varVals[i] != term.varVals[i]) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return varVals.hashCode();
	}

}
