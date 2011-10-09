package com.uco.compsci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class CompSciHelperActivity extends Activity {
	private Intent i;
	private Types t = new Types();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//drawView = new DrawView(this, "QuakerSort");
		//setContentView(drawView);

		setContentView(R.layout.mainscreen);
	}

	public void onClick(View target) {
		switch (target.getId()) {
		case R.id.radioSort:
			break;
		case R.id.radioTrees:
			break;
		case R.id.bubble:
			t.setType("BubbleSort");
			i = new Intent("com.uco.compsci.DrawViewActivity");
			i.putExtra("type", t.whatType.ordinal());
			startActivity(i);
			break;
		case R.id.quaker:
			t.setType("QuakerSort");
			i = new Intent("com.uco.compsci.DrawViewActivity");
			i.putExtra("type", t.whatType.ordinal());
			startActivity(i);
			//drawView = new DrawView(this, "QuakerSort");
			//setContentView(drawView);
			//break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}

	/*@Override
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
	}*/

}