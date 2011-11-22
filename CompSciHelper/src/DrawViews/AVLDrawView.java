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
import android.widget.Toast;

public class AVLDrawView extends View {
	public final static String SIMPLE_SINGLE_ROTATION = "ssingle";
	private final int SIMPLE_SINGLE_ROTATION_FRAMES = 36;
	private final int SIMPLE_SINGLE_BREAK_POINTS[] = { 0, 31, 32, 33, 34, 35 };
	
	public final static String SINGLE_ROTATION = "single";
	private final int SINGLE_ROTATION_FRAMES = 46;
	private final int SINGLE_BREAK_POINTS[] = { 0, 22, 23, 41, 42, 43, 44, 45 };
		
	public final static String DOUBLE_ROTATION = "double";
	private final int DOUBLE_ROTATION_FRAMES = 88;
	private final int DOUBLE_BREAK_POINTS[] = { 0, 1, 16, 17, 38, 39, 40, 41, 63, 64, 82, 83, 84, 85, 86, 87 };

	private TextView displayTextView;
	private Resources appR;
	private Thread animationHandle = null;
	private TreeAnimate animate = new TreeAnimate(this);
	private Paint paint = new Paint();
	private String type;
	private int frameSpot = 0;
	private boolean paused = true;
	private int stringColorCounter = 0;
	
	private String displayText[][] = {
			{ 		
					"Node 15 is now critically imbalanced with the addition of node 25. Since node 15 is right heavy and 15's child on that side is also right heavy a single rotation on node 15 is needed to restore balance.",
					"Now that the tree has been restructured we need to reset the weights of the nodes.",
					"Node 15 is now evenly weighted on the left and right.",
					"Node 25 remains the same weight and is no longer causing the imbalance.",
					"Node 20 is now evenly weighted on the left and right.",
					"The single rotation is now complete and the tree is back in balance."},
			{
					"Node 10 becomes critically imbalanced with the addition of node 15. Node 10 is heavy on the right side. Since 10's child on that side is heavy on its left a double rotation is needed in order to restore balance.",
					"First we will perform a right rotation on node 25.",
					"We need to change the right child of 10 to now point at node 20.",
					"Additionally the right child of 20 is now node 25.", 
					"The first rotation is complete. Now let's reset the weights on the nodes for this first rotation.",
					"Node 25 is now right heavy by one instead of left heavy.", 
					"Node 20 is now right heavy by one instead of left heavy.",
					"Essentially what we have done is take the \"dog leg\" imbalance and turned it into and imbalance that can be solved by another single rotation, this time on node 10. This final rotation will restore balance in the tree.",
					"Swap 15 to the right child of 10.",
					"Node 15 is now the right child of 10.",
					"We now weed to reset the weights of the nodes in the tree.",
					"Node 10 is now even weighted on both the right and the left.",
					"Node 15 has been successfully inserted into the tree.",
					"Node 25 remains right heavy by one.",
					"Node 20 is now evenly weighted on the left and right sides.",
					"The double rotation on the tree is now complete and the tree is back in balance."}, 
			{		
					"The addition of node 30 creates a critical imbalance on node 10. 10's child on the right heavy side is also right heavy so a single left rotation is needed to restore balance.",
					"To preserve order in the tree the left child of node 20 needs to be switched to the right child of node 10.",
					"Node 15 is now the right child of node 10.",
					"Now that the tree has been restructured we need to reset the weights of the nodes within the tree.",
					"Node 10 is now even weighted on left and right.",
					"Node 25 remains right heavy by one.",
					"Node 20 is now evely weighted on the left and right.",
					"The single left rotation is now complete and the tree's balance has been restored."}
	
	};

	public AVLDrawView(Context context, Display d, String type) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.type = type;
		paint.setAntiAlias(true);
		appR = context.getResources();
	}

	public void setTextView(TextView tv) {
		displayTextView = tv;
	}

	public void setType(String s) {
		if (!type.equals(SIMPLE_SINGLE_ROTATION) || !type.equals(DOUBLE_ROTATION) || !type.equals(SINGLE_ROTATION))
			type = SIMPLE_SINGLE_ROTATION;
		else
			type = s;
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
		//displayTextView.setText(displayTextView.getText() + "\nframeSpot: " + frameSpot);

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
		if (type.equals(SIMPLE_SINGLE_ROTATION))
			return displayText[0];
		else if (type.equals(DOUBLE_ROTATION))
			return displayText[1];
		else if (type.equals(SINGLE_ROTATION))
			return displayText[2];
		return null;
	}

	public void incTextCounter() {
		if (type.equals(SIMPLE_SINGLE_ROTATION))
				stringColorCounter = (stringColorCounter + 1)
				% displayText[0].length;
		else if (type.equals(DOUBLE_ROTATION))
				stringColorCounter = (stringColorCounter + 1)
				% displayText[1].length;
		else if(type.equals(SINGLE_ROTATION))
				stringColorCounter = (stringColorCounter + 1)
				% displayText[2].length;
	}

	public void incFrame() {
		if (!paused) {
			frameSpot += 1;
			if (type.equals(SIMPLE_SINGLE_ROTATION)) {
				frameSpot %= SIMPLE_SINGLE_ROTATION_FRAMES;
				for (int x : SIMPLE_SINGLE_BREAK_POINTS) {
					if (x == frameSpot) {
						animate.changeAnimating();
						changePause();
					}
				}
			}
			else if (type.equals(SINGLE_ROTATION)) {
				frameSpot %= SINGLE_ROTATION_FRAMES;
				for (int x : SINGLE_BREAK_POINTS) {
					if (x == frameSpot) {
						animate.changeAnimating();
						changePause();
					}
				}
			}			
			else if (type.equals(DOUBLE_ROTATION)) {
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
