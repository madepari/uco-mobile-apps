package com.uco.compsci;

import DrawViews.TreeDrawView;
import Shapes.Types;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;

public class TreeDrawViewActivity extends Activity {
	private Types t = new Types();
	private int spot;
	TreeDrawView drawView = null;
	Display display;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		display = getWindowManager().getDefaultDisplay();

		// gets data put in by me on other
		// spot = getIntent().getExtras().getInt("type");
		// t.setType(spot);
		// spot = getIntent().getExtras().getInt("size");

		drawView = new TreeDrawView(this);
		// drawView = new DrawView(this, t, spot);
		setContentView(drawView);
		drawView.setDisplaySize(display.getHeight(), display.getWidth());
		// setContentView(R.layout.buttons);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		try {

			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
				drawView.addItem();
			// drawView.removeItem();

			else
				super.onKeyDown(keyCode, event);

		} catch (Exception e) {
			super.onKeyDown(keyCode, event);
		} finally {
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}
}
