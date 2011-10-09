package com.uco.compsci;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class DrawViewActivity extends Activity {
	private Types t = new Types();
	private int spot;
	DrawView drawView = null;//new DrawView(this, "BubbleSort");
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		spot = getIntent().getExtras().getInt("type");
		t.setType(spot);
		drawView = new DrawView(this, t);
		setContentView(drawView);
		
		//setContentView(R.layout.buttons);
	}

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
