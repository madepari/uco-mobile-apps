package com.uco.compsci;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

public class DrawViewActivity extends Activity {
	private Types t = new Types();
	private int spot;
	DrawView drawView = null;//new DrawView(this, "BubbleSort");
	Display display;  

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		display = getWindowManager().getDefaultDisplay();
		
		
		
		//gets data put in by me on other
		spot = getIntent().getExtras().getInt("type");
		t.setType(spot);
		spot = getIntent().getExtras().getInt("size");
		
		
		
		drawView = new DrawView(this, t, spot);
		setContentView(drawView);
		drawView.setDisplaySize(display.getHeight(), display.getWidth());
		//setContentView(R.layout.buttons);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		try {
			if (drawView.getWait() || drawView.getFinished()) {

				if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
						&& !drawView.getFinished())
					drawView.changeWait();
				// drawView.removeItem();

				else
					super.onKeyDown(keyCode, event);
			}

		} catch (Exception e) {
			super.onKeyDown(keyCode, event);
		} finally {
			return true;
		}
	}
}
