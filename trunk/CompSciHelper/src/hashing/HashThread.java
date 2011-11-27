package hashing;

import java.util.List;

import DrawViews.DrawView;
import Shapes.Shape;

public class HashThread {
	protected boolean finished = false;
	protected List<Shape> Shapes = null;
	private DrawView dview;
	protected boolean wait = false;
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

	public boolean getWait() {
		return wait;
	}

	public boolean getFinished() {
		return finished;
	}

	protected void redraw() {
		dview.postInvalidate();
	}

	public void run() {
		while (running) {
			if (wait == false) {
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
}