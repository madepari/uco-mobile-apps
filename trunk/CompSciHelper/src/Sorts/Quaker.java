package Sorts;

import android.graphics.Color;

import com.uco.compsci.DrawView;
import com.uco.compsci.Shape;
import com.uco.compsci.Square;

public class Quaker extends Sort {

	private int spotInSort = -1;
	private final int PIECES = 20;
	final long MILLIS = 1;
	private int way = 1;
	private int counter = 0;

	public Quaker(int size, DrawView d) {
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
		super.inView(spotInSort);
		if (spotInSort == -1) {
			spotInSort++;
			Shapes.get(spotInSort).setColor(Color.BLUE);
			Shapes.get(spotInSort + way).setColor(Color.BLUE);
		} else if (!this.finished && !this.testSpot(spotInSort)) {
			s1 = Shapes.get(spotInSort);
			s2 = Shapes.get(spotInSort + way);
			if (way == 1 && s1.getNumb() > s2.getNumb()) {
				swapShapes(spotInSort + way, spotInSort);
				this.incRunFinished();
			}
			if (way == -1 && s1.getNumb() < s2.getNumb()) {
				swapShapes(spotInSort, spotInSort + way);
				this.incRunFinished();
			}
			Shapes.get(spotInSort).setColor(Color.WHITE);
			Shapes.get(spotInSort + way).setColor(Color.WHITE);

			if ((way == 1 && spotInSort == Shapes.size() - 2)
					|| (way == -1 && spotInSort == 1)
					|| this.testSpot(spotInSort + way) == true || (counter > 1 && this.testSpot(spotInSort + (2*way)) == true)) {
				if (this.getRunFinished() == 0) {
					for (Shape s : Shapes) {
						s.setColor(Color.GREEN);
						super.redraw();
					}
					super.finished = true;
					super.destroyThread();
					return;
				}
				if (this.testSpot(spotInSort + way) == true) {
					this.setSpot(spotInSort, 1);
					Shapes.get(spotInSort).setColor(Color.GREEN);
				}
				else if (counter > 1 && this.testSpot(spotInSort + (2*way)) == true){
					this.setSpot(spotInSort+ way, 1);
					Shapes.get(spotInSort+ way).setColor(Color.GREEN);
				}
				this.setSpot(spotInSort + way, 1);
				Shapes.get(spotInSort + way).setColor(Color.GREEN);
				way = -way;
				counter++;
				this.resetRunFinished();
				Shapes.get(spotInSort).setColor(Color.BLUE);
				Shapes.get(spotInSort + way).setColor(Color.BLUE);
			} else {
				spotInSort += way;
				Shapes.get(spotInSort).setColor(Color.BLUE);
				Shapes.get(spotInSort + way).setColor(Color.BLUE);
			}

		}
	}
}
