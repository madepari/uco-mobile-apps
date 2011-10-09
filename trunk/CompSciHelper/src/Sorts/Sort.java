package Sorts;

import java.util.List;

import com.uco.compsci.DrawView;
import com.uco.compsci.Shape;

public abstract class Sort {
	protected int[] holder;
	protected boolean finished = false;
	protected int RunFinished = 1;
	protected List<Shape> Shapes = null;
	private DrawView dview;
	protected boolean wait = true;
	protected boolean running = true;

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