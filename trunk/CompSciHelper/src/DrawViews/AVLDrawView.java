package DrawViews;

import Trees.TreeAnimate;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

public class AVLDrawView extends View {
	public final String SINGLE_ROTATION = "single";
	private final int SINGLE_ROTATION_FRAMES = 27;
	private final int SINGLE_BREAK_POINTS[] = { 0, 24, 25, 26 };
	public final String DOUBLE_ROTATION = "double";
	private final int DOUBLE_ROTATION_FRAMES = 39;
	private final int DOUBLE_BREAK_POINTS[] = { 0, 17, 25, 26, 35, 36, 37, 38 };

	TextView displayTextView;
	Resources appR;
	Thread animationHandle = null;
	TreeAnimate animate = new TreeAnimate(this);
	Paint paint = new Paint();
	private String type = DOUBLE_ROTATION;
	private int width, height;
	private String displayText[][] = {
			{ "Node 15 is out of balance",
					"Node 15 is moved to be the left child of node 20",
					"change the weight of node 15",
					"change the weight of node 20" },
			{
					"This inbalance needs to be fixed with a double rotation.\n\nFirst we rotate around Node 25.",
					"After first rotation.",
					"Now we do a left rotation around Node 10",
					"Node 15 is changed to be the right child of Node 10", "",
					"Change weight of 10", "Change weight of 20",
					"Change weight of 25" } };

	private int frameSpot = 0;
	private boolean paused = true;
	private int stringColorCounter = 0;

	public AVLDrawView(Context context, Display d) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		paint.setAntiAlias(true);
		appR = context.getResources();
		setDisplayDim(d);
	}

	public void setTextView(TextView tv) {
		displayTextView = tv;
	}

	public void setType(String s) {
		if (!type.equals(SINGLE_ROTATION))
			type = SINGLE_ROTATION;
		else
			type = SINGLE_ROTATION;
	}

	// creates the extra thread needed for animation
	public void start() {

		animationHandle = new Thread(new Runnable() {
			@Override
			public void run() {
				TreeAnimate.run();
			};
		});
		// thread stays in loop until sort is completed
		animationHandle.start();
	}

	public void killThread() {
		TreeAnimate.stopThread();
	}

	public void onDraw(Canvas canvas) {
		int resID = appR.getIdentifier("com.uco.compsci:drawable/" + type
				+ frameSpot, null, null);
		Bitmap bd = BitmapFactory.decodeResource(getResources(), resID);
		canvas.drawBitmap(bd, 0, 0, paint);

		displayTextView.setText(getDisplay()[stringColorCounter]);
	}

	private void setDisplayDim(Display display) {
		width = display.getWidth();
		height = display.getHeight();
	}

	public boolean isPaused() {
		return paused;
	}

	public void changePause() {
		paused = !paused;
	}

	public void buttonChangePause() {
		changePause();
		animate.changeAnimating();
		incTextCounter();
	}

	public String[] getDisplay() {
		if (type.equals(SINGLE_ROTATION))
			return displayText[0];
		else if (type.equals(DOUBLE_ROTATION))
			return displayText[1];
		return null;
	}

	public void incTextCounter() {
		if (type.equals(SINGLE_ROTATION))
			stringColorCounter = (stringColorCounter + 1)
					% displayText[0].length;
		else if (type.equals(DOUBLE_ROTATION))
			stringColorCounter = (stringColorCounter + 1)
					% displayText[1].length;
	}

	public void incFrame() {
		if (!paused) {
			frameSpot += 1;
			if (type.equals(SINGLE_ROTATION)) {
				frameSpot %= SINGLE_ROTATION_FRAMES;
				for (int x : SINGLE_BREAK_POINTS) {
					if (x == frameSpot) {
						animate.changeAnimating();
						changePause();
					}
				}
			}
			if (type.equals(DOUBLE_ROTATION)) {
				frameSpot %= DOUBLE_ROTATION_FRAMES;
				for (int x : DOUBLE_BREAK_POINTS) {
					if (x == frameSpot) {
						animate.changeAnimating();
						changePause();
					}
				}
			}
		}

	}

}