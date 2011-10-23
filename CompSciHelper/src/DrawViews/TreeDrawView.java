package DrawViews;

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

	/*
	public void removeItem() {
		// Shapes.remove(Shapes.size() - 1);
		this.invalidate();
	}
	 */
	
	public TreeDrawView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
		paint.setAntiAlias(true);
		
		//creates the Root
		bt = new BinaryTree(new Node(new Circle(Math.abs(rnd.nextInt() % 50))));
	}

	//draws by traversing the list
	@Override
	public void onDraw(Canvas canvas) {
		bt.TraverseDraw(bt.getRoot(), canvas, paint);
	}

	//if a move is necessary it moves them
	private void moveItems(float curr_spotX, float new_spotX, float curr_spotY,
			float new_spotY) {
		float DeltaX = 0, DeltaY = 0;
		DeltaX = new_spotX - curr_spotX;
		DeltaY = new_spotY - curr_spotY;

		bt.TraverseMove(bt.getRoot(), DeltaX, DeltaY);

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
