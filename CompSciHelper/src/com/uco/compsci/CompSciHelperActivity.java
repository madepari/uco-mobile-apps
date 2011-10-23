package com.uco.compsci;

import Shapes.Types;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompSciHelperActivity extends Activity {
	private Intent i;
	private Types t = new Types();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
	}

	public void onClick(View target) {
		TextView numbNodes = (TextView) findViewById(R.id.enterNodeNumber);
		
		//minium size for sorts will be 4
		int size = 4;
		if (Integer.parseInt(numbNodes.getText().toString()) > 4) {
			size = Integer.parseInt(numbNodes.getText().toString());
		}
		switch (target.getId()) {
		case R.id.radioSort:
			//need to fix does not do what planned
			//hides trees
			ChangeButtons("sorts");
			break;
		case R.id.radioTrees:
			//hides sorts
			ChangeButtons("trees");
			break;
		case R.id.bubble:
			//set type to bubblesort
			t.setType("BubbleSort");
			//create new sortActivity
			i = new Intent("com.uco.compsci.DrawViewActivity");
			//put in we want bubble and the size
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		case R.id.quaker:
			t.setType("QuakerSort");
			//create new sortActivity
			i = new Intent("com.uco.compsci.DrawViewActivity");
			//put in we want Quaker and the size
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		case R.id.binarytree:
			//create new binary tree activity
			i = new Intent("com.uco.compsci.TreeDrawViewActivity");
			startActivity(i);
			break;
		}
	}

	private void ChangeButtons(String type) {
		Button binary = (Button) findViewById(R.id.binarytree);
		Button bubble = (Button) findViewById(R.id.bubble);
		Button quaker = (Button) findViewById(R.id.quaker);
		if (type == "sorts") {
			binary.setVisibility(Button.INVISIBLE);
			bubble.setVisibility(Button.VISIBLE);
			quaker.setVisibility(Button.VISIBLE);
		} else if (type == "trees") {
			binary.setVisibility(Button.VISIBLE);
			bubble.setVisibility(Button.INVISIBLE);
			quaker.setVisibility(Button.INVISIBLE);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.selection, menu);
		// It is important to return true to see the menu
		return true;
	}
}