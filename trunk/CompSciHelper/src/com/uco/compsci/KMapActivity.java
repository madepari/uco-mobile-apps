package com.uco.compsci;

import kmap.KMapController;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class KMapActivity extends Activity {
	final int PADDING = 5;
	TextView display;
	KMapController KMapControl = new KMapController(KMapController.VARIABLE_5);
	private int fiveVarCounter = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LinearLayout linearlayout = new LinearLayout(this);
		linearlayout.setOrientation(LinearLayout.VERTICAL);
		linearlayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		linearlayout.addView(createTable());
		if (KMapControl.getKMapSize() == KMapController.VARIABLE_5)
			linearlayout.addView(createTable());
		display = new TextView(this);
		display.setTextSize(15);
		display.setText("Display");
		display.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT));
		linearlayout.addView(display);

		setContentView(linearlayout);
		super.onCreate(savedInstanceState);
	}

	public TableLayout createTable() {
		int comboCounter = 0;
		TableLayout tl = new TableLayout(this);
		TableRow tr = new TableRow(this);
		CheckBox c;
		TextView text;
		for (int x = 0; x < KMapControl.getTopDisplay().length; x++) {
			text = new TextView(this);
			if (KMapControl.getKMapSize() == KMapController.VARIABLE_5) {
				if (x == 0) {
					fiveVarCounter += 1;
					text.setText(fiveVarCounter + "");
				} else
					text.setText(KMapControl.getTopDisplay()[x]);
			} else {
				text.setText(KMapControl.getTopDisplay()[x]);
			}
			text.setPadding(PADDING, PADDING, PADDING, PADDING);
			tr.addView(text);
		}
		tl.addView(tr);

		for (int x = 0; x < KMapControl.getLeftDisplay().length; x++) {
			tr = new TableRow(this);
			text = new TextView(this);
			text.setText(KMapControl.getLeftDisplay()[x]);
			text.setPadding(PADDING, PADDING, PADDING, PADDING);
			tr.addView(text);

			for (int y = 1; y < KMapControl.getTopDisplay().length; y++) {
				c = new CheckBox(this);
				if (KMapControl.getKMapSize() == KMapController.VARIABLE_5
						&& fiveVarCounter == 1)
					c.setId(KMapControl.getTableLayout()[comboCounter]+16);
				else
					c.setId(KMapControl.getTableLayout()[comboCounter]);
				c.setButtonDrawable(R.drawable.customcheck);
				c.setPadding(PADDING, PADDING, PADDING, PADDING);

				c.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						String s = "Minterm: " + arg0.getId() + "\n"
								+ "State: ";
						if (arg1 == false)
							s += "0";
						else
							s += "1";
						display.setText(s);
					}
				});
				tr.addView(c);
				comboCounter += 1;
			}
			tl.addView(tr);

		}

		return tl;
		/*
		 * c.setLayoutParams(new
		 * LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		 * tr.setLayoutParams(new
		 * LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		 * tr.addView(c); tl.addView(tr);
		 */
	}
	/*
	 * public void onClick(View Target) { CheckBox cb = (CheckBox)
	 * findViewById(Target.getId()); TextView tv; switch (Target.getId()) { case
	 * R.id.S0: tv = (TextView) findViewById(R.id.m0); if(cb.isChecked()){
	 * tv.setText("1"); } else{ tv.setText("0"); } break; case R.id.S1: tv =
	 * (TextView) findViewById(R.id.m1); if(cb.isChecked()){ tv.setText("1"); }
	 * else{ tv.setText("0"); } break; case R.id.S2: tv = (TextView)
	 * findViewById(R.id.m2); if(cb.isChecked()){ tv.setText("1"); } else{
	 * tv.setText("0"); } break; case R.id.S3: tv = (TextView)
	 * findViewById(R.id.m3); if(cb.isChecked()){ tv.setText("1"); } else{
	 * tv.setText("0"); } break; case R.id.S4: tv = (TextView)
	 * findViewById(R.id.m4); if(cb.isChecked()){ tv.setText("1"); } else{
	 * tv.setText("0"); } break; case R.id.S5: tv = (TextView)
	 * findViewById(R.id.m5); if(cb.isChecked()){ tv.setText("1"); } else{
	 * tv.setText("0"); } break; case R.id.S6: tv = (TextView)
	 * findViewById(R.id.m6); if(cb.isChecked()){ tv.setText("1"); } else{
	 * tv.setText("0"); } break; case R.id.S7: tv = (TextView)
	 * findViewById(R.id.m7); if(cb.isChecked()){ tv.setText("1"); } else{
	 * tv.setText("0"); } break; } }
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { return
	 * super.onCreateOptionsMenu(menu); }
	 */

}
