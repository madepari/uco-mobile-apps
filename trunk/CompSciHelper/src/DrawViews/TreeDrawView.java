package DrawViews;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Shapes.*;
import Sorts.*;
import Trees.BinaryTree;
import Trees.Node;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TreeDrawView extends View implements OnTouchListener {
	private Random rnd = new Random();
	private float ySpot = 0.0f;
	private float xSpot = 0.0f;
	static final float MAX_SPEED = 10.0f;
	BinaryTree bt;
	Paint paint = new Paint();
	Types type;

	private int width;
	private int height;

	public void setType(Types.type t) {
		type.whatType = t;
	}

	public void addItem() {
		
		Node node = new Node(new Circle(0, 0, 40, Math.abs(rnd.nextInt() % 50)));
		bt.addChild(bt.getRoot(), node);
		this.invalidate();
	}

	public void removeItem() {
		// Shapes.remove(Shapes.size() - 1);
		this.invalidate();
	}

	// public TreeDrawView(Context context, Types stype, int size) {
	public TreeDrawView(Context context) {
		super(context);
		// type = stype;
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);

		bt = new BinaryTree(new Node(new Circle(Math.abs(rnd.nextInt() % 50))));
		/*
		 * for (int x = 0; x < size - 1; x++) { s = new Square(s,
		 * Math.abs(rnd.nextInt() % 50)); Shapes.add(s); switch (type.whatType)
		 * { case BubbleSort: break; } }
		 */
	}

	@Override
	public void onDraw(Canvas canvas) {
		bt.TraverseDraw(bt.getRoot(), canvas, paint);
	}

	private void moveItems(float curr_spotX, float new_spotX, float curr_spotY,
			float new_spotY) {
		float DeltaX = 0, DeltaY = 0;
		DeltaX = new_spotX - curr_spotX;
		DeltaY = new_spotY - curr_spotY;

		bt.TraverseMove(bt.getRoot(), DeltaX, DeltaY);

	}

	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			xSpot = event.getX();
			ySpot = event.getY();
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			moveItems(xSpot, event.getX(), ySpot, event.getY());
			ySpot = event.getY();
			xSpot = event.getX();
		}
		invalidate();
		return true;
	}

	public void setDisplaySize(int height, int width) {
		this.width = width;
		this.height = height;
	}

	/*
	 * public void inView(Shape s) { if(s.getY() > 0 && s.getY() < height -
	 * 150){} else while(s.getY() < 20){ moveItems(s.getY(), 20); } if(s.getY()
	 * > height - 150){ while(s.getY() > 30){ moveItems(s.getY(), 30); } } }
	 */
}
