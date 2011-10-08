package com.uco.compsci;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class CompSciHelperActivity extends Activity {
	private DrawView drawView;
	private LinearLayout parentContainer;
	Runnable sortThread;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		drawView = new DrawView(this);
		// createParentContainer();
		// setContentView(parentContainer);
		setContentView(drawView);
		drawView.requestFocus();
		// drawView.setType("BubbleSort");
	}

	public void onClick(View target) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		// inflater.inflate(R.menu.mymenu, menu);
		// It is important to return true to see the menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!drawView.lock) {
			drawView.changeLock();
			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
				drawView.IncSort();
			// drawView.removeItem();
			// else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
			// drawView.addItem();
			drawView.changeLock();
		}
		return true;
	}

	private void createParentContainer() {
		LinearLayout drawContainer = new LinearLayout(this);
		LinearLayout buttonContainer = new LinearLayout(this);
		buttonContainer.addView((LinearLayout) findViewById(R.layout.buttons));
		drawContainer.addView(drawView);

		parentContainer = new LinearLayout(this);

		parentContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		parentContainer.setOrientation(LinearLayout.VERTICAL);

		parentContainer.addView(buttonContainer);
		parentContainer.addView(drawView);
	}
}