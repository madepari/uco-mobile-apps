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

public class AVLDrawView extends View {
	public final String SINGLE_ROTATION = "single";
	private final int SINGLE_ROTATION_FRAMES = 27;
	private final int SINGLE_BREAK_POINTS[] = { 0, 24, 25, 26 };
	public final String DOUBLE_ROTATION = "double";
	private final int DOUBLE_ROTATION_FRAMES = 39;
	private final int DOUBLE_BREAK_POINTS[] = { 0, 17, 25, 26, 35, 36, 37, 38};
	
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
					"change the weight of node 20" }, { "test2", "test2.1" } };
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

		paint.setTextSize(50);
		paint.setColor(Color.RED);

		//canvas.drawText(displayText[0][stringColorCounter], 10, 500, paint);

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
		stringColorCounter += 1;
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
