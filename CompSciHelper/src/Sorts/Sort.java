package Sorts;

import java.util.List;

import DrawViews.DrawView;
import Shapes.Shape;

/*
 * Abstract class since we have many sorts
 */

public abstract class Sort {
	protected int[] holder;
	protected boolean finished = false;
	protected int RunFinished = 1;
	protected List<Shape> Shapes = null;
	private DrawView dview;
	protected boolean wait = true;
	protected boolean running = true;

	protected void inView(int spot) {
		if (spot > -1)
			dview.inView(Shapes.get(spot));
	}

	public void changeWait() {
		wait = !wait;
	}

	public void destroyThread() {
		running = !running;
	}

	public int getRunFinished() {
		return RunFinished;
	}

	public boolean getWait() {
		return wait;
	}

	public void incRunFinished() {
		RunFinished += 1;
	}

	public boolean getFinished() {
		return finished;
	}

	protected abstract void incSort();

	public abstract void isFinished();

	protected void redraw() {
		dview.postInvalidate();
	}

	public void resetRunFinished() {
		RunFinished = 0;
	}

	/*
	 * This is needed since only one thread can redraw the drawview.
	 * Since it only draws when the thread is completed we need a secondary thread.
	 * First thread calls PostInvalidate then yields.  Allowing this thread to draw.
	 */
	public void run() {
		while (running) {
			if (wait == false) {
				incSort();
				changeWait();
				redraw();
			}
			Thread.yield();
		}
	}

	public void setDview(DrawView dview) {
		this.dview = dview;
	}

	public void setList(List<Shape> S) {
		Shapes = S;
	}

	protected abstract void setSpot(int spot, int set);

	protected abstract void swapShapes(int low, int high);

	protected abstract boolean testSpot(int spot);
}