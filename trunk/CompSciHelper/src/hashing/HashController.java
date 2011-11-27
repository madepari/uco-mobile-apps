package hashing;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

public class HashController {
	ArrayList<HashBlock> hashTable = new ArrayList<HashBlock>();
	ArrayList<Integer> list = new ArrayList<Integer>();

	public HashController() {
		HashBlock hb = new HashBlock();
		hashTable.add(hb);
		for (int x = 0; x < 9; x++) {
			hb = new HashBlock(hb);
			hashTable.add(hb);
		}
	}

	public void addNumbers() {
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(0);
		list.add(1);
	}

	public void addToHash(){
		if(list.size() > 1){
			int value = list.get(0);
			int hashValue = value % 10;
			
			if(hashTable.get(hashValue).getStoredHashedValue() == -1){
				hashTable.get(hashValue).setStoredHashValue(value);
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

}
