package com.uco.compsci;

import kmap.KMapController;
import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	KMapController KMapControl = new KMapController(KMapController.VARIABLE_3);
	private int fiveVarCounter;
	LinearLayout linearlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		createLinear();
		super.onCreate(savedInstanceState);
	}

	public void createLinear() {
		fiveVarCounter = -1;
		linearlayout = new LinearLayout(this);
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
					text.setTextColor(Color.YELLOW);
					text.setText(fiveVarCounter + "  ");
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
					c.setId(KMapControl.getTableLayout()[comboCounter] + 16);
				else
					c.setId(KMapControl.getTableLayout()[comboCounter]);
				c.setButtonDrawable(R.drawable.customcheck);
				c.setPadding(PADDING, PADDING, PADDING, PADDING);

				c.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						KMapControl.setVariableAtLocation(arg0.getId(), arg1);
						String s = "Minterm: " + arg0.getId() + "\n"
								+ "State: ";
						if (arg1 == false)
							s += "0";
						else
							s += "1";
						s += "\nBinary: "
								+ KMapControl.getBinarySpot(arg0.getId());
						s += "\nVars: "
								+ KMapControl.getStringVariableAtLocation(arg0
										.getId());
						display.setText(s);
					}
				});
				tr.addView(c);
				comboCounter += 1;
			}
			tl.addView(tr);

		}
		return tl;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.kmenu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.var3:
			KMapControl = new KMapController(KMapController.VARIABLE_3);
			createLinear();
			break;
		case R.id.var4:
			KMapControl = new KMapController(KMapController.VARIABLE_4);
			createLinear();
			break;
		case R.id.var5:
			KMapControl = new KMapController(KMapController.VARIABLE_5);
			createLinear();
			break;
		case R.id.vieweq:
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.vieweq);
			dialog.setTitle("Quine–McCluskey Algorithm");
			dialog.setCancelable(true);

			TextView text = (TextView) dialog.findViewById(R.id.TextView01);
			text.setText("\nEquation:\n" + KMapControl.getEquation() + "\n\nReduced Equation:\n" + KMapControl.getReducedEquation() + "\n\n");

			Button button = (Button) dialog.findViewById(R.id.Button01);
			button.setText("Close");
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.cancel();
				}
			});
			dialog.show();
			break;
		}
		return true;
	}
}
