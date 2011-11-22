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

		switch (target.getId()) {

		case R.id.sortoption:
			i = new Intent("com.uco.compsci.MenuActivity");
			i.putExtra("option", "sorts");
			startActivity(i);
			break;
		case R.id.kmapoption:
			i = new Intent("com.uco.compsci.KMapActivity");
			startActivity(i);
			break;
		case R.id.avloption:
			i = new Intent("com.uco.compsci.MenuActivity");
			i.putExtra("option", "avls");
			startActivity(i);
			break;
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
		// inflater.inflate(R.menu., menu);
		// It is important to return true to see the menu
		return true;
	}
}