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

	// intent is created here
	public void onClick(View target) {
		TextView numbNodes = (TextView) findViewById(R.id.enterNodeNumber);
		int size = 4;
		if (Integer.parseInt(numbNodes.getText().toString()) > 4) {
			size = Integer.parseInt(numbNodes.getText().toString());
		}
		switch (target.getId()) {
		case R.id.radioSort:
			ChangeButtons("sorts");
			break;
		case R.id.radioTrees:
			ChangeButtons("trees");
			break;
		case R.id.bubble:
			t.setType("BubbleSort");
			i = new Intent("com.uco.compsci.DrawViewActivity");
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		case R.id.quaker:
			t.setType("QuakerSort");
			i = new Intent("com.uco.compsci.DrawViewActivity");
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		case R.id.binarytree:
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