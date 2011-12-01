package hashing;

import Shapes.Shape;
import Shapes.Square;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class HashBlock {
	private int allocatedNumber = -1;;
	private Square block;
	private HashBlock chainLink = null;

	public HashBlock() {
		allocatedNumber = 0;
		block = new Square(100, 10, -1);
	}

	public HashBlock(Square s){
		block = new Square(100, 10, -1);
	}
	
	public HashBlock(HashBlock s) {
		allocatedNumber = s.getAllocatedNumber() + 1;
		block = new Square(s.getBlock(), -1);
	}

	public void Draw(Canvas canvas, Paint paint) {
		block.Draw(canvas, paint);
		if (chainLink != null){
			chainLink.Draw(canvas, paint);
		}
		if (allocatedNumber != -1) {
			paint.setColor(Color.RED);
			paint.setTextSize(35);
			canvas.drawText(allocatedNumber + " ", block.getX() - 20,
					block.getCenterY(), paint);
		}
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

	public int getStoredHashedValue() {
		return block.getNumb();
	}

	public void setStoredHashValue(int value) {
		block.setNumb(value);
	}

	public void move(float deltaY) {
		block.setY(block.getY() + deltaY);
	}

	public void setMainActive() {
		block.setColor(Color.BLUE);
	}

	public void setActive() {
		block.setColor(Color.GREEN);
	}

	public void setNotActive() {
		block.setColor(Color.WHITE);
	}

	public HashBlock getChainLink() {
		return chainLink;
	}

	public void setChainLink(HashBlock chainLink) {
		this.chainLink = chainLink;
	}
	
	public void addChain(int n){
		if(this.chainLink != null){
			this.chainLink.addChain(n);
		}else {
			this.chainLink = new HashBlock(new Square(block.getX() + 120, block.getY(), n));
		}
	}
}
