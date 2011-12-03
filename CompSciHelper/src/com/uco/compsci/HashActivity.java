package com.uco.compsci;

import DrawViews.HashDrawView;
import Shapes.Types;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;

public class HashActivity extends Activity {
	private Types t = new Types();
	private int spot;
	private int[] values;
	HashDrawView drawView = null;
	Display display;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		values = getIntent().getIntArrayExtra("values");
		if (values.length != 0)
			drawView = new HashDrawView(this, getIntent().getExtras().getInt(
					"type"), values);
		else
			drawView = new HashDrawView(this, getIntent().getExtras().getInt(
					"type"));

		setContentView(drawView);
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
}
