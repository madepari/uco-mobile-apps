package com.uco.compsci;

import DrawViews.DrawView;
import Shapes.Types;
import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;

public class DrawViewActivity extends Activity {
	private Types t = new Types();
	private int spot;
	DrawView drawView = null;//new DrawView(this, "BubbleSort");
	Display display;  

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		display = getWindowManager().getDefaultDisplay();
				
		//gets data passed through by calling activity
		spot = getIntent().getExtras().getInt("type");
		t.setType(spot);
		spot = getIntent().getExtras().getInt("size");
		
		
		//DrawView(Context, Bubble/Quaker/etc, size_of_sort) 
		drawView = new DrawView(this, t, spot);
		setContentView(drawView);

		//passes the max_height and max_width to drawView for when displayed sort if off screen
		// it will then put animation back on screen
		drawView.setDisplaySize(display.getHeight(), display.getWidth());
	}

	@SuppressWarnings("finally")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		try {
			//if it is currently drawing we will wait
			if (drawView.getWait() || drawView.getFinished()) {
				//traps the volume down button here will not pass it to system
				//uses volume down for conitinuing sort
				if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
						&& !drawView.getFinished())
					drawView.changeWait();
				else
					super.onKeyDown(keyCode, event);
			}

		} catch (Exception e) {
			super.onKeyDown(keyCode, event);
		} finally {
			return true;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		//inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}
	
	
	//onDestory is called when activity is being destroyed by the system
	//very important to override this because of our extra thread
	@Override
	protected void onDestroy() {
		//stops the thread
		drawView.stopThread();
		super.onDestroy();
	}
}
