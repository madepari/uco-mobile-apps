package com.uco.compsci;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class KMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layoutkmap);
		super.onCreate(savedInstanceState);
	}

	public void onClick(View Target) {
		TextView tv = (TextView) findViewById(R.id.textView1);
		switch (Target.getId()) {
		case R.id.S0:
			tv.setText("S0");
			break;
		case R.id.S1:
			tv.setText("S1");
			break;
		case R.id.S2:
			tv.setText("S2");
			break;
		case R.id.S3:
			tv.setText("S3");
			break;
		case R.id.S4:
			tv.setText("S4");
			break;
		case R.id.S5:
			tv.setText("S5");
			break;
		case R.id.S6:
			tv.setText("S6");
			break;
		case R.id.S7:
			tv.setText("S7");
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

}
