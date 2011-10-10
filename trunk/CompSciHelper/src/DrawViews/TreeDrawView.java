package DrawViews;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Shapes.*;
import Sorts.*;

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
	static final float MAX_SPEED = 10.0f;
	List<Shape> Shapes = new LinkedList<Shape>();
	Paint paint = new Paint();
	Shape s;
	Types type;

	private int width;
	private int height;

	public void setType(Types.type t) {
		type.whatType = t;
	}

	public void clearItems() {
		Shapes.clear();
		invalidate();
	}

	public void addItem() {
		Circle c = new Circle(0, 0, 40, 5);
		Shapes.get(0).addChild(c);
		Shapes.add(c);
		this.invalidate();
	}

	public void removeItem() {
		Shapes.remove(Shapes.size() - 1);
		this.invalidate();
	}

	//public TreeDrawView(Context context, Types stype, int size) {
	public TreeDrawView(Context context){
		super(context);
		//type = stype;
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);

		s = new Circle(50);
		Shapes.add(s);
		/*for (int x = 0; x < size - 1; x++) {
			s = new Square(s, Math.abs(rnd.nextInt() % 50));
			Shapes.add(s);
			switch (type.whatType) {
			case BubbleSort:
				break;
			}
		}*/
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


	public void setDisplaySize(int height, int width) {
		this.width = width;
		this.height = height;
	}

	public void inView(Shape s) {
		if(s.getY() > 0 && s.getY() < height - 150){}
		else
			while(s.getY() < 20){
				moveItems(s.getY(), 20);
			}
			if(s.getY() > height - 150){
				while(s.getY() > 30){
					moveItems(s.getY(), 30);
				}
			}
	}
}
