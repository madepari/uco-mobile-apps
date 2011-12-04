package Sorts;

import DrawViews.DrawView;
import Shapes.Shape;
import Shapes.Square;
import android.graphics.Color;

public class Quick extends Sort {

	private int lowSpot = -1;
	private int highSpot = -1;
	private final int PIECES = 20;
	final long MILLIS = 1;

	// Constructor
	public Quick(int size, DrawView d) {
		super.holder = new int[size];
		for (int x = 0; x < size - 1; x++)
			super.holder[x] = 0;
			/*super.holder is an array
			* if the entire array is 1 then the 
			*sort is complete
			*/			
		this.setDview(d);
	}

	@Override
	public void isFinished() {
		//checks list to see if complete
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
		//tests a set spot if it is done
		if (super.holder[spot] == 0)
			return false;
		else
			return true;
	}

	//Setter
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
		/*
		 * Figures out the amount to move the shapes to keep
		 * the animiation nice and smooth
		 */
		speed = (Shapes.get(low).getY() - temp2.getY()) / PIECES;
		boolean complete = false;
		while (!complete) {
			counter += 1;
			
			//Moves the shapes until they are in the other spot 
			if (temp2.getY() != Shapes.get(low).getY()) {
				Shapes.get(low).setY(Shapes.get(low).getY() + (-speed));
			}
			if (temp1.getY() != Shapes.get(high).getY()) {
				Shapes.get(high).setY(Shapes.get(high).getY() + (speed));
			}
			
			//Or if the counter reaches the determined pieces we broke
			//the animiation into
			if (counter == PIECES) {
				complete = true;
				Shapes.get(low).setY(temp2.getY());
				Shapes.get(high).setY(temp1.getY());
			}
			//calls invalidator
			super.redraw();
			/* need to yield thread to allow other\
			 * thread to draw
			 */
			
			Thread.yield();
			try {
				Thread.sleep(MILLIS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//sets shapes to correct opposing spots
		Shape temp = Shapes.get(low);
		Shapes.set(low, Shapes.get(high));
		Shapes.set(high, temp);
	}

	
	
	@Override
	public void incSort() {
		/*
		 * Moves to the next spot in the algorithm on the sort
		 */
		Shape s1, s2;
		if (lowSpot == -1 && highSpot == -1) {
			//First check

			lowSpot = 0;
			highSpot = Shapes.size() - 1;
			Shapes.get(lowSpot).setColor(Color.BLUE);
			Shapes.get(highSpot).setColor(Color.BLUE);
			this.resetRunFinished();
			this.isFinished();
		} else if (!this.finished && highSpot != lowSpot) {
			/*
			 * Actively is going through the sort
			 */
			s1 = Shapes.get(lowSpot);
			s2 = Shapes.get(highSpot);
			if (s1.getNumb() > s2.getNumb()) {
				swapShapes(lowSpot, highSpot);
				Shapes.get(highSpot).setColor(Color.WHITE);
				highSpot -=1;
				Shapes.get(highSpot).setColor(Color.BLUE);
			} else {
				Shapes.get(lowSpot).setColor(Color.WHITE);
				lowSpot += 1;
				Shapes.get(lowSpot).setColor(Color.BLUE);
			}
		} else if(highSpot == lowSpot){
			//leftSide first
			Shapes.get(lowSpot).setColor(Color.GREEN);
			highSpot -= 1;			
			lowSpot = 0;
		}
		if (super.finished) {
			// fin
			super.destroyThread();
		}

	}

}
