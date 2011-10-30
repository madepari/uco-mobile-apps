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
	/*
	private Types t = new Types();
	private int spot;
	*/
	TreeDrawView drawView = null;
	Display display;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		display = getWindowManager().getDefaultDisplay();
		/**************************
		 * Not currently used but needed
		 * for when multiple trees can be selected
		 * 
		 * spot = getIntent().getExtras().getInt("type");
		 * t.setType(spot);
		 * 
		 **************************/		
		
		drawView = new TreeDrawView(this);
		setContentView(drawView);
		drawView.setDisplaySize(display.getHeight(), display.getWidth());
	}

	@SuppressWarnings("finally")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		try {
			//traps the volume down button here will not pass it to system
			//uses volume down for conitinuing sort
			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
				drawView.addItem();
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
		//inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}
}
