package com.uco.compsci;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class KMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layoutkmap);
		super.onCreate(savedInstanceState);
	}

	public void onClick(View Target) {
		CheckBox cb = (CheckBox) findViewById(Target.getId());
		TextView tv;
		switch (Target.getId()) {
		case R.id.S0:
			tv = (TextView) findViewById(R.id.m0);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S1:
			tv = (TextView) findViewById(R.id.m1);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S2:
			tv = (TextView) findViewById(R.id.m2);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S3:
			tv = (TextView) findViewById(R.id.m3);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S4:
			tv = (TextView) findViewById(R.id.m4);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S5:
			tv = (TextView) findViewById(R.id.m5);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S6:
			tv = (TextView) findViewById(R.id.m6);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		case R.id.S7:
			tv = (TextView) findViewById(R.id.m7);
			if(cb.isChecked()){
				tv.setText("1");
			}
			else{
				tv.setText("0");
			}
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

}
