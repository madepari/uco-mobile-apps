package DrawViews;

import hashing.HashController;

import java.util.Random;

import Shapes.Circle;
import Shapes.Types;
import Trees.BinaryTree;
import Trees.Node;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class HashDrawView extends View implements OnTouchListener {
	private Random rnd = new Random();
	private float ySpot = 0.0f;
	private float xSpot = 0.0f;
	static final float MAX_SPEED = 10.0f;
	HashController hashCont;
	Paint paint = new Paint();

	private int width;
	private int height;

	public void addItem() {
		hashCont.addToHash();
		invalidate();
	}
	
	public HashDrawView(Context context, int type) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);
		hashCont = new HashController(type);
		//bt = new BinaryTree(new Node(new Circle(Math.abs(rnd.nextInt() % 50))));
	}

	//draws by traversing the list
	@Override
	public void onDraw(Canvas canvas) {
		hashCont.draw(canvas, paint);
	}

	//if a move is necessary it moves them
	private void moveItems(float curr_spotX, float new_spotX, float curr_spotY,
			float new_spotY) {
		float DeltaY = 0;
		DeltaY = new_spotY - curr_spotY;

		hashCont.move(DeltaY);

	}

	//handles movement of the tree in the X and Y axis
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

	//setter
	public void setDisplaySize(int height, int width) {
		this.width = width;
		this.height = height;
	}
}
