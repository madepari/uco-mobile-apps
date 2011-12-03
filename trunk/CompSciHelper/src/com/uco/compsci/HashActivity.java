package com.uco.compsci;

import hashing.Probing;
import DrawViews.HashDrawView;
import Shapes.Types;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HashActivity extends Activity {
	private final String DISPLAY_LEFT = "Value";
	private final String DISPLAY_CENTER = "Mod Spot";
	private final String DISPLAY_RIGHT = "Collision";

	private Types t = new Types();
	private int spot;
	private int[] values;
	HashDrawView drawView = null;
	Display display;

	TableLayout tl;

	TextView currentLeft;
	TextView currentCenter;
	TextView currentRight;

	TextView nextLeft;
	TextView nextCenter;
	TextView nextRight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout rl = new RelativeLayout(this);
		values = getIntent().getIntArrayExtra("values");
		if (values.length != 0)
			drawView = new HashDrawView(this, getIntent().getExtras().getInt(
					"type"), values);
		else
			drawView = new HashDrawView(this, getIntent().getExtras().getInt(
					"type"));
		rl.addView(drawView);

		setUpTable();
		rl.addView(tl);

		setContentView(rl);
		setDisplayValues();
	}

	@SuppressWarnings("finally")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// try {
		// if it is currently drawing we will wait
		// if (drawView.getWait() || drawView.getFinished()) {

		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
			drawView.addItem();
		else
			super.onKeyDown(keyCode, event);
		// }

		// } catch (Exception e) {
		// super.onKeyDown(keyCode, event);
		// } finally {
		setDisplayValues();
		return true;
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		// inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}

	// onDestory is called when activity is being destroyed by the system
	// very important to override this because of our extra thread
	@Override
	protected void onDestroy() {
		// stops the thread
		// drawView.stopThread();
		super.onDestroy();
	}

	protected void setUpTable() {
		tl = new TableLayout(this);

		TableRow tr = new TableRow(this);

		// tr.setBackgroundResource(R.drawable.cell_shape);

		TextView displayLeft = new TextView(this);
		TextView displayCenter = new TextView(this);
		TextView displayRight = new TextView(this);

		displayLeft.setText(DISPLAY_LEFT);
		setTextUp(displayLeft);

		displayCenter.setText(DISPLAY_CENTER);
		setTextUp(displayCenter);

		displayRight.setText(DISPLAY_RIGHT);
		setTextUp(displayRight);

		tr.addView(displayLeft);
		tr.addView(displayCenter);
		tr.addView(displayRight);

		tl.addView(tr);

		// ///////////////////////////////

		displayLeft = new TextView(this);
		displayCenter = new TextView(this);
		displayRight = new TextView(this);

		tr = new TableRow(this);
		// tr.setBackgroundResource(R.drawable.cell_shape);

		setTextUp(displayLeft);
		currentLeft = displayLeft;

		setTextUp(displayCenter);
		currentCenter = displayCenter;

		setTextUp(displayRight);
		currentRight = displayRight;

		tr.addView(displayLeft);
		tr.addView(displayCenter);
		tr.addView(displayRight);

		tl.addView(tr);

		// ///////////////////////////////

		displayLeft = new TextView(this);
		displayCenter = new TextView(this);
		displayRight = new TextView(this);

		tr = new TableRow(this);
		// tr.setBackgroundResource(R.drawable.cell_shape);

		setTextUp(displayLeft);
		nextLeft = displayLeft;

		setTextUp(displayCenter);
		nextCenter = displayCenter;

		setTextUp(displayRight);
		nextRight = displayRight;

		tr.addView(displayLeft);
		tr.addView(displayCenter);
		tr.addView(displayRight);

		tl.addView(tr);

		// android:layout_alignParentTop="true"
		// android:layout_alignParentRight="true"
		// android:layout_marginRight="40dp"
		// android:layout_marginTop="37dp"

		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);

		rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		Resources r = getResources();
		rlp.setMargins(
				(int) Math
						.rint(TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 175,
								r.getDisplayMetrics())),
				(int) Math.rint(TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 37, r.getDisplayMetrics())),
				0, 0);
		tl.setLayoutParams(new RelativeLayout.LayoutParams(rlp));
	}

	protected void setDisplayValues() {
		currentLeft.setText(drawView.getDisplay("CNum"));
		currentCenter.setText(drawView.getDisplay("CMod"));
		if (drawView.getProbeType() != Probing.CHAINING_PROBING)
			currentRight.setText(drawView.getDisplay("CNum"));

		nextLeft.setText(drawView.getDisplay("NNum"));
		nextCenter.setText(drawView.getDisplay("NMod"));
		if (drawView.getProbeType() != Probing.CHAINING_PROBING)
			currentRight.setText(drawView.getDisplay("CNum"));
	}

	protected void setTextUp(TextView t) {
		t.setPadding(7, 0, 7, 0);
	}
}
