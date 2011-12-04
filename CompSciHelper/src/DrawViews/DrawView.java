package DrawViews;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Shapes.Shape;
import Shapes.Square;
import Shapes.Types;
import Sorts.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {
	private Sort sortType;
	private Random rnd = new Random();
	private float ySpot = 0.0f;
	static final float MAX_SPEED = 10.0f;
	List<Shape> Shapes = new LinkedList<Shape>();
	Paint paint = new Paint();
	Shape s;
	Types type;
	Thread sortHandle = null;
	private int width;
	private int height;
	
	//creates the extra thread needed for animation
	public void start() {
		sortHandle = new Thread(new Runnable() {
			@Override
			public void run() {
				sortType.run();
			};
		});
		//thread stays in loop until sort is completed
		sortHandle.start();
	}
	
	//set the type ex Bubble/Quaker/etc
	public void setType(Types.type t) {
		type.whatType = t;
	}

	//clear the list
	public void clearItems() {
		Shapes.clear();
		invalidate();
	}

	public DrawView(Context context, Types stype, int size) {
		super(context);
		type = stype;
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);

		//creates the size of 
		s = new Square(50, 70, Math.abs(rnd.nextInt() % 50));
		Shapes.add(s);
		for (int x = 0; x < size - 1; x++) {
			s = new Square(s, Math.abs(rnd.nextInt() % 50));
			Shapes.add(s);
			switch (type.whatType) {
			case BubbleSort:
				sortType = new Bubble(Shapes.size(), this);
				sortType.setList(Shapes);
				break;
			case QuakerSort:
				sortType = new Quaker(Shapes.size(), this);
				sortType.setList(Shapes);
				break;
			case QuickSort:
				sortType = new Quick(Shapes.size(), this);
				sortType.setList(Shapes);
				break;
			}
		}
		start();
	}

	//draw the stuff
	@Override
	public void onDraw(Canvas canvas) {
		Shape cur = null;
		for (int spot = 0; spot < Shapes.size(); spot++) {
			cur = Shapes.get(spot);
			paint.setColor(cur.getColor());
			cur.Draw(canvas, paint);
		}

	}

	//traverses list and moves all items
	private void moveItems(float curr_spot, float new_spot) {
		for (Shape s : Shapes) {
			if (curr_spot < new_spot)
				s.setY(s.getY() + MAX_SPEED);
			else if (curr_spot > new_spot)
				s.setY(s.getY() - MAX_SPEED);
		}

	}

	//handles the onTouch for user movement of the Y axis
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

	//changes the wait causing the activity to now wait
	//before trying to go to next position in sort
	public void changeWait() {
		sortType.changeWait();
	}

	//getter
	public boolean getWait() {
		return sortType.getWait();
	}

	//getter
	public boolean getFinished() {
		return sortType.getFinished();
	}

	//Stops extra thread
	public void stopThread() {
		sortType.destroyThread();
	}

	//setter
	public void setDisplaySize(int height, int width) {
		this.width = width;
		this.height = height;
	}

	//tests if current shape is in the view of the screen
	//if not moves the list so that it is
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
