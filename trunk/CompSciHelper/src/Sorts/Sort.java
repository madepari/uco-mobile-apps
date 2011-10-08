package Sorts;

import java.util.List;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.uco.compsci.DrawView;
import com.uco.compsci.Shape;

public abstract class Sort {
	protected int[] holder;
	protected boolean finished = false;
	protected int RunFinished = 1;
	protected List<Shape> Shapes = null;
	protected DrawView dview;
	protected boolean wait = true;

	public abstract void isFinished();

	public abstract boolean testSpot(int spot);

	public abstract void setSpot(int spot, int set);

	public abstract void incSort();

	public abstract void swapShapes(int low, int high);

	public void incRunFinished() {
		RunFinished += 1;
	}

	public void setList(List<Shape> S) {
		Shapes = S;
	}

	public void resetRunFinished() {
		RunFinished = 0;
	}

	public int getRunFinished() {
		return RunFinished;
	}

	protected void redraw() {
		dview.postInvalidate();
	}

	protected void changeWait() {
		wait = !wait;
	}

	public void run() {
		while (true) {
			if (wait == false)
				incSort();
			Thread.yield();
		}
	}
}