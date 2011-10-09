package Sorts;

import android.graphics.Color;

import com.uco.compsci.DrawView;
import com.uco.compsci.Shape;
import com.uco.compsci.Square;

public class Bubble extends Sort {
	
	private int spotInSort = -1;
	private final int PIECES = 20;
	final long MILLIS = 1;

	public Bubble(int size, DrawView d) {
		super.holder = new int[size];
		for (int x = 0; x < size - 1; x++)
			super.holder[x] = 0;
		this.setDview(d);
	}

	@Override
	public void isFinished() {
		for (int x : holder) {
			if (x == 0) {
				super.finished = false;
				return;
			}
		}
		super.finished = true;
		return;
	}

	@Override
	public boolean testSpot(int spot) {
		if (super.holder[spot] == 0)
			return false;
		else
			return true;
	}

	@Override
	public void setSpot(int spot, int set) {
		super.holder[spot] = set;
	}

	@Override
	public void swapShapes(final int low, final int high) {

		Shape temp1, temp2;
		temp1 = new Square(Shapes.get(low));
		temp2 = new Square(Shapes.get(high));
		float speed;
		int counter = 0;
		speed = (Shapes.get(low).getY() - temp2.getY()) / PIECES;
		boolean complete = false;
		while (!complete) {
			counter += 1;
			if (temp2.getY() != Shapes.get(low).getY()) {
				Shapes.get(low).setY(Shapes.get(low).getY() + (-speed));
			}
			if (temp1.getY() != Shapes.get(high).getY()) {
				Shapes.get(high).setY(Shapes.get(high).getY() + (speed));
			}
			if (counter == PIECES) {
				complete = true;
				Shapes.get(low).setY(temp2.getY());
				Shapes.get(high).setY(temp1.getY());
			}
			super.redraw();

			Thread.yield();
			try {
				Thread.sleep(MILLIS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Shape temp = Shapes.get(low);
		Shapes.set(low, Shapes.get(high));
		Shapes.set(high, temp);
	}

	@Override
	public void incSort() {
		Shape s1, s2;
		if (spotInSort == -1) {
			spotInSort++;
			Shapes.get(spotInSort).setColor(Color.BLUE);
			Shapes.get(spotInSort + 1).setColor(Color.BLUE);
			this.resetRunFinished();
			this.isFinished();
		} else if (!this.finished && !this.testSpot(spotInSort)) {
			s1 = Shapes.get(spotInSort);
			s2 = Shapes.get(spotInSort + 1);
			if (s1.getNumb() > s2.getNumb()) {
				swapShapes(spotInSort + 1, spotInSort);
				this.incRunFinished();
			}
			Shapes.get(spotInSort).setColor(Color.WHITE);
			Shapes.get(spotInSort + 1).setColor(Color.WHITE);
			
			//test if we are checking last 2 items
			if (spotInSort == Shapes.size() - 2 || this
					.testSpot(spotInSort + 1) == true) {
				this.setSpot(spotInSort, 1);
				this.setSpot(spotInSort+1, 1);
				Shapes.get(spotInSort + 1).setColor(Color.GREEN);
				if(spotInSort == 0){
					this.setSpot(spotInSort, 1);
					Shapes.get(spotInSort).setColor(Color.GREEN);
					this.isFinished();
				}
				else if (this.getRunFinished() == 0) {
					for (Shape s : Shapes) {
						s.setColor(Color.GREEN);
					}
					super.finished = true;
				}
				spotInSort = -1;
			} else {
				
				spotInSort += 1;
				Shapes.get(spotInSort).setColor(Color.BLUE);
				Shapes.get(spotInSort + 1).setColor(Color.BLUE);
			}
						
		}
		if(super.finished){
			//fin
			super.destroyThread();					
		}

	}
}
