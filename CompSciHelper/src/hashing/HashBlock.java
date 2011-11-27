package hashing;

import Shapes.Shape;
import Shapes.Square;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HashBlock extends HashThread {
	private int allocatedNumber;
	private Square block;

	public HashBlock() {
		allocatedNumber = 0;
		block = new Square(100, 10, -1);
	}

	public HashBlock(HashBlock s) {
		allocatedNumber = s.getAllocatedNumber() + 1;
		block = new Square(s.getBlock(), -1);
	}

	public void Draw(Canvas canvas, Paint paint) {
		block.Draw(canvas, paint);
		paint.setColor(Color.RED);
		paint.setTextSize(35);
		canvas.drawText(allocatedNumber + " ", block.getX() - 20,
				block.getCenterY(), paint);
	}

	public int getAllocatedNumber() {
		return allocatedNumber;
	}

	public void setAllocatedNumber(int allocatedNumber) {
		this.allocatedNumber = allocatedNumber;
	}

	public Shape getBlock() {
		return block;
	}

	public void setBlock(Square block) {
		this.block = block;
	}
	
	public int getStoredHashedValue(){
		return block.getNumb();
	}

	public void setStoredHashValue(int value) {
		block.setNumb(value);	
	}	
	
	public void move(float deltaY) {
		block.setY(block.getY() + deltaY);
	}

}
