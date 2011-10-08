package com.uco.compsci;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Sorts.Bubble;
import Sorts.Sort;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {
	private Sort sortType;
	private Random rnd = new Random();
	private float ySpot = 0.0f;
	static final float MAX_SPEED = 10.0f;
	protected boolean lock = false;
	List<Shape> Shapes = new LinkedList<Shape>();
	Paint paint = new Paint();
	Shape s;
	String type = "BubbleSort";
	// Handler sortHandle;
	Thread sortHandle;

	public void start() {
		sortHandle = new Thread(new Runnable() {
			@Override
			public void run() {
				sortType.run();
			};
		});
		sortHandle.start();
		/*
		 * sortHandle = new Handler(); sortHandle.postAtFrontOfQueue(new
		 * Runnable() {
		 * 
		 * @Override public void run() { sortType.run(); }; });
		 */
		//Thread.yield();
	}

	public void setType(String t) {
		type = t;
	}

	public void IncSort() {
		if (!lock) {
			sortType.incSort();
			invalidate();
		}
	}

	public void clearItems() {
		Shapes.clear();
		invalidate();
	}

	public void addItem() {
		Shapes.add(new Square(Shapes.get(Shapes.size() - 1), Math.abs(rnd
				.nextInt() % 50)));
		this.invalidate();
	}

	public void removeItem() {
		Shapes.remove(Shapes.size() - 1);
		this.invalidate();
	}

	public DrawView(Context context) {
		super(context);

		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);
		if (type == "BubbleSort") {
			s = new Square(50, 70, Math.abs(rnd.nextInt() % 50));
			Shapes.add(s);
			for (int x = 0; x < 6; x++) {
				s = new Square(s, Math.abs(rnd.nextInt() % 50));
				Shapes.add(s);
			}
			sortType = new Bubble(Shapes.size(), this);
			sortType.setList(Shapes);
		}
		start();
	}

	@Override
	public void onDraw(Canvas canvas) {
		Shape cur = null;
		for (int spot = 0; spot < Shapes.size(); spot++) {
			cur = Shapes.get(spot);
			paint.setColor(cur.getColor());
			cur.Draw(canvas, paint);
		}

	}

	private void moveItems(float curr_spot, float new_spot) {
		for (Shape s : Shapes) {
			if (curr_spot < new_spot)
				s.setY(s.getY() + MAX_SPEED);
			else if (curr_spot > new_spot)
				s.setY(s.getY() - MAX_SPEED);
		}

	}

	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			ySpot = event.getY();
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			moveItems(ySpot, event.getY());
			ySpot = event.getY();
		}
		invalidate();
		return true;
	}

	public void changeLock() {
		lock = !lock;
	}

	public boolean getLock() {
		return lock;
	}

}
