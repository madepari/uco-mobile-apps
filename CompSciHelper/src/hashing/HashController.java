package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.graphics.Canvas;
import android.graphics.Paint;

public class HashController {
	public static int NO_VALUE = -2;
	ArrayList<HashBlock> hashTable = new ArrayList<HashBlock>();
	ArrayList<Integer> list = new ArrayList<Integer>();

	Probing probe = null;

	int value;
	int hashValue = NO_VALUE;

	public HashController(int type, int[] values) {
		probe = new Probing(type, 10);
		HashBlock hb = new HashBlock();
		hashTable.add(hb);
		for (int x = 0; x < 9; x++) {
			hb = new HashBlock(hb);
			hashTable.add(hb);
		}
		int counter = 0;
		for (int x : values) {
			if (probe.getType() != Probing.CHAINING_PROBING) {
				if (counter == 9)
					break;
				counter += 1;
			}
			list.add(x);
		}
	}

	public HashController(int type) {
		probe = new Probing(type, 10);
		HashBlock hb = new HashBlock();
		hashTable.add(hb);
		for (int x = 0; x < 9; x++) {
			hb = new HashBlock(hb);
			hashTable.add(hb);
		}
		addNumbers();
	}

	public void addNumbers() {
		list.add(7);
		list.add(27);
		list.add(8);
		list.add(9);
		list.add(0);
		list.add(1);
		list.add(17);

	}

	public void addToHash() {
		if (list.size() > 0) {

			if (hashValue == NO_VALUE) {
				value = list.get(0);
				hashValue = value % 10;
				probe.setProbeSpot(hashValue);
				hashTable.get(hashValue).setMainActive();
			}
			if (probe.getType() == Probing.CHAINING_PROBING) {
				if (hashTable.get(hashValue).getStoredHashedValue() == -1)
					hashTable.get(hashValue).setStoredHashValue(value);
				else
					hashTable.get(hashValue).addChain(value);
				list.remove(0);
				hashValue = NO_VALUE;
			} else {
				if (hashTable.get(probe.getProbeSpot()).getStoredHashedValue() == -1) {

					hashTable.get(probe.getProbeSpot()).setStoredHashValue(
							value);
					list.remove(0);
					probe.reset();
					hashTable.get(hashValue).setNotActive();
					hashValue = NO_VALUE;
					value = NO_VALUE;

				} else {
					hashTable.get(probe.getProbeSpot()).setNotActive();
					probe.incProbe();
					hashTable.get(probe.getProbeSpot()).setActive();
				}
			}
		}
	}

	public void draw(Canvas canvas, Paint paint) {
		for (HashBlock hb : hashTable) {
			hb.Draw(canvas, paint);
		}
	}

	public void move(float deltaY) {
		for (HashBlock hb : hashTable) {
			hb.move(deltaY);
		}

	}

	public String getDisplay(String filter) {
		if (filter.equals("CNum")) {
			if (list.size() > 0)
				return list.get(0) + "";
			else
				return "";
		}
		else if(filter.equals("NNum")){
			if (list.size() > 1)
				return list.get(1) + "";
			else
				return "";
		}
		else if(filter.equals("CMod")){
			if (list.size() > 0)
				return list.get(0) % 10 + "";
			else
				return "";
		}
		else if(filter.equals("NMod")){
			if (list.size() > 1)
				return list.get(1) % 10 + "";
			else
				return "";
		}
		return filter;

	}

	public int getProbeType() {
		return probe.getType();
	}

}
