package com.uco.compsci;

import Shapes.Types;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {
	private Intent i;
	private Types t = new Types();
	private String opt;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		opt = getIntent().getExtras().getString("option");
		if (opt.equals("avls")) {
			setContentView(R.layout.avlmenu);
		} else if (opt.equals("sorts")) {
			setContentView(R.layout.sort_menu);
		}

	}

	public void onClick(View target) {
		TextView numbNodes = (TextView) findViewById(R.id.enterNodeNumber);

		// minium size for sorts will be 4
		int size = 4;
		if (opt.equals("sorts") && Integer.parseInt(numbNodes.getText().toString()) > 4) {
			size = Integer.parseInt(numbNodes.getText().toString());
		}
		switch (target.getId()) {
		case R.id.bubble:
			// set type to bubblesort
			t.setType("BubbleSort");
			// create new sortActivity
			i = new Intent("com.uco.compsci.DrawViewActivity");
			// put in we want bubble and the size
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		case R.id.shaker:
			t.setType("QuakerSort");
			// create new sortActivity
			i = new Intent("com.uco.compsci.DrawViewActivity");
			// put in we want Quaker and the size
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		case R.id.singlerotation:
			i = new Intent("com.uco.compsci.AVLAnimator");
			// put in we want Quaker and the size
			i.putExtra("type", "single_rotation");

			startActivity(i);
			break;
		case R.id.simplesinglerotation:
			i = new Intent("com.uco.compsci.AVLAnimator");
			// put in we want Quaker and the size
			i.putExtra("type", "simple_single_rotation");

			startActivity(i);
			break;
		case R.id.doublerotation:
			i = new Intent("com.uco.compsci.AVLAnimator");
			// put in we want Quaker and the size
			i.putExtra("type", "double_rotation");

			startActivity(i);
			break;
		}

	}

}
