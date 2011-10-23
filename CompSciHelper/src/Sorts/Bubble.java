package Sorts;

import DrawViews.DrawView;
import Shapes.Shape;
import Shapes.Square;
import android.graphics.Color;

public class Bubble extends Sort {

	private int spotInSort = -1;
	private final int PIECES = 20;
	final long MILLIS = 1;

	// Constructor
	public Bubble(int size, DrawView d) {
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
		/* Handles the swapping of shapes
		 * moves them into correct places
		 * only moves two at a time
		 * so swap place might not be the final swap
		 * (Ex bottom of list after first full run through list)
		 */
		Shape temp1, temp2;
		/* temps needed to keep the shapes in correct spot
		 * due to floating point we need the exact spot so
		 * sot appears correctly.
		 * Since we are manipulating the shapes we need exact spot
		 * for when the total movement is complete the two shapes
		 * have changed spots correctly
		 */
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
		super.inView(spotInSort);
		if (spotInSort == -1) {
			//First check
			spotInSort++;
			Shapes.get(spotInSort).setColor(Color.BLUE);
			Shapes.get(spotInSort + 1).setColor(Color.BLUE);
			this.resetRunFinished();
			this.isFinished();
		} else if (!this.finished && !this.testSpot(spotInSort)) {
			/*
			 * Actively is going through the sort
			 */
			s1 = Shapes.get(spotInSort);
			s2 = Shapes.get(spotInSort + 1);
			if (s1.getNumb() > s2.getNumb()) {
				swapShapes(spotInSort + 1, spotInSort);
				this.incRunFinished();
			}
			Shapes.get(spotInSort).setColor(Color.WHITE);
			Shapes.get(spotInSort + 1).setColor(Color.WHITE);

			// test if we are checking last 2 items
			if (spotInSort == Shapes.size() - 2
					|| this.testSpot(spotInSort + 1) == true) {
				/*
				 * last 2 items are needed because on the way down we know
				 * the last check is in its correct spot
				 */
				this.setSpot(spotInSort, 1);
				this.setSpot(spotInSort + 1, 1);
				Shapes.get(spotInSort + 1).setColor(Color.GREEN);
				if (spotInSort == 0) {
					this.setSpot(spotInSort, 1);
					Shapes.get(spotInSort).setColor(Color.GREEN);
					this.isFinished();
				} else if (this.getRunFinished() == 0) {
					for (Shape s : Shapes) {
						s.setColor(Color.GREEN);
					}
					super.finished = true;
				}
				spotInSort = -1;
			} else {
				//not at the end so move on
				spotInSort += 1;
				Shapes.get(spotInSort).setColor(Color.BLUE);
				Shapes.get(spotInSort + 1).setColor(Color.BLUE);
			}

		}
		if (super.finished) {
			// fin
			super.destroyThread();
		}

	}

}
