package com.uco.compsci;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CompSciHelperActivity extends Activity {
	private DrawView drawView = null;
	private LinearLayout parentContainer;
	Runnable sortThread;
	private Intent dv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// drawView = new DrawView(this, "QuakerSort");
		// setContentView(drawView);

		setContentView(R.layout.mainscreen);

		// drawView = (DrawView) findViewById(R.id.drawView1);
		// setContentView(R.layout.buttons);
		// this.addContentView(drawView, new LayoutParams(
		// LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		// setContentView(R.layout.buttons);

		// createParentContainer();
		// setContentView(parentContainer);
	}

	public void onClick(View target) {
		switch (target.getId()) {
		case R.id.radioSort:
			break;
		case R.id.radioTrees:
			break;
		case R.id.bubble:
			drawView = new DrawView(this, "BubbleSort");
			setContentView(drawView);
			break;
		case R.id.quaker:
			drawView = new DrawView(this, "QuakerSort");
			setContentView(drawView);
			break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.bubble:
			drawView = new DrawView(this, "BubbleSort");
			dv = new Intent(this, DrawView.class);
			dv.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
			startActivity(dv);
			break;
		case R.id.quaker:
			drawView = new DrawView(this, "QuakerSort");
			dv = new Intent(this, DrawView.class);
			dv.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			startActivity(dv);
			break;

		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (drawView.getWait() || drawView.getFinished()) {

			if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
					&& !drawView.getFinished())
				drawView.changeWait();
			// drawView.removeItem();
			else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
				drawView.stopThread();
				drawView = new DrawView(this, "QuakerSort");
				setContentView(drawView);
			} else if(keyCode == KeyEvent.KEYCODE_BACK){
				if(dv == null)
					super.onKeyDown(keyCode, event);
				else
					stopService(dv);
			}
			else
				super.onKeyDown(keyCode, event);
		}
		return true;

	}

	private void createParentContainer() {
		LinearLayout drawContainer = new LinearLayout(this);
		LinearLayout buttonContainer = new LinearLayout(this);
		drawContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		drawContainer.setOrientation(LinearLayout.VERTICAL);
		// drawView = new DrawView(this);
		drawContainer.addView(drawView);

		buttonContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		buttonContainer.setOrientation(LinearLayout.HORIZONTAL);
		Button add = new Button(this);
		add.setText("Add");
		buttonContainer.addView(add);

		parentContainer = new LinearLayout(this);

		parentContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		parentContainer.setOrientation(LinearLayout.VERTICAL);

		parentContainer.addView(drawView);
		parentContainer.addView(buttonContainer);
	}
}