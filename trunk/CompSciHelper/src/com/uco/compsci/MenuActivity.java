package com.uco.compsci;

import java.util.ArrayList;

import hashing.Probing;
import Shapes.Types;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {
	private final String TAG = "MOBAPPS";
	private AlertDialog.Builder itemChoice;
	private final CharSequence[] itemChoices = { "Edit", "Move", "Delete" };

	private Intent i;
	private Types t = new Types();
	private String opt;

	// LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
	private ArrayList<String> listItems = new ArrayList<String>();

	// DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	private ArrayAdapter<String> adapter;

	protected static int editSpot;
	protected static int clickedSpot;
	protected static boolean moving = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		opt = getIntent().getExtras().getString("option");
		if (opt.equals("avls")) {
			setContentView(R.layout.avlmenu);
		} else if (opt.equals("sorts")) {
			setContentView(R.layout.sort_menu);
		} else if (opt.equals("hash")) {
			setContentView(R.layout.hashmenu);
			setHashListView();
		}
		itemChoice = new AlertDialog.Builder(this);

	}

	public void onAddItem(View target) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Add Value");
		alert.setMessage("Add a number to the hash function.\nNote only valid inputs are 1-50.");
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		alert.setView(input);

		if (target != null) {
			// Set an EditText view to get user input

			alert.setPositiveButton("Add Item",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String addValue = input.getText().toString();
							if (testAddValue(addValue)) {
								listItems.add(addValue);
							}
							adapter.notifyDataSetChanged();
						}
					});
			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) { // Canceled.
						}
					});
			alert.show();
		} else {
			input.setText(listItems.get(editSpot));
			// used for edit
			alert.setPositiveButton("Save",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							String addValue = input.getText().toString();
							if (testAddValue(addValue)) {
								listItems.set(editSpot, addValue);
							}

						}
					});
			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) { // Canceled.
						}
					});
			alert.show();
		}
	}

	public boolean testAddValue(String addVal) {
		try {
			int numb = Integer.parseInt(addVal);
			if (numb <= 50 && numb >= 1)
				return true;

			Toast.makeText(super.getBaseContext(),
					"Value must be a number and between 1-50",
					Toast.LENGTH_SHORT).show();
			return false;
		} catch (Exception e) {
			Toast.makeText(super.getBaseContext(),
					"Value must be a number and between 1-50",
					Toast.LENGTH_SHORT).show();
		}
		return false;

	}

	public void setHashListView() {
		ListView lv = (ListView) findViewById(R.id.hashList);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listItems);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.e(TAG, "arg2: " + arg2 + "   arg3: " + arg3);
				clickedSpot = arg2;
				if (!moving) {
					itemChoice.setTitle("Options");
					itemChoice.setItems(itemChoices,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int item) {
									if (!moving) {
										if (itemChoices[item].equals("Delete")) {
											listItems.remove(clickedSpot);

										} else if (itemChoices[item]
												.equals("Edit")) {
											editSpot = clickedSpot;
											onAddItem(null);
										} else if (itemChoices[item]
												.equals("Move")) {
											Toast.makeText(
													getApplicationContext(),
													"Click the item you wish to move it before.",
													Toast.LENGTH_LONG).show();
											editSpot = clickedSpot;
											moving = true;
										}

									} else {

									}
									adapter.notifyDataSetChanged();
								}
							});

					AlertDialog alert = itemChoice.create();
					alert.show();
				} else {
					String temp = listItems.get(clickedSpot);
					listItems.set(clickedSpot, listItems.get(editSpot));
					listItems.set(editSpot, temp);
					moving = false;

				}
				adapter.notifyDataSetChanged();

			}

		});

	}

	public void onClick(View target) {
		TextView numbNodes = (TextView) findViewById(R.id.enterNodeNumber);

		// minium size for sorts will be 4
		int size = 4;
		if (opt.equals("sorts")
				&& Integer.parseInt(numbNodes.getText().toString()) > 4) {
			size = Integer.parseInt(numbNodes.getText().toString());
		}
		switch (target.getId()) {

		/*
		 * SORTS
		 */
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
		case R.id.quick:
			t.setType("QuickSort");
			// create new sortActivity
			i = new Intent("com.uco.compsci.DrawViewActivity");
			// put in we want Quaker and the size
			i.putExtra("type", t.whatType.ordinal());
			i.putExtra("size", size);
			startActivity(i);
			break;
		/*
		 * Data Trees
		 */
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
		case R.id.avldelete:
			i = new Intent("com.uco.compsci.AVLAnimator");
			i.putExtra("type", "delete");

			startActivity(i);
			break;
		case R.id.btreeinsert:
			i = new Intent("com.uco.compsci.AVLAnimator");
			i.putExtra("type", "btreeinsert");

			startActivity(i);
			break;

		/*
		 * HASHING
		 */
		case R.id.linearprobe:
			i = new Intent("com.uco.compsci.HashActivity");
			// put in we want Quaker and the size
			i.putExtra("type", Probing.LINEAR_PROBING);
			i.putExtra("values", getListIntoInteger());
			startActivity(i);
			break;
		case R.id.quadradicprobe:
			i = new Intent("com.uco.compsci.HashActivity");
			// put in we want Quaker and the size
			i.putExtra("type", Probing.QUADRATIC_PROBING);
			i.putExtra("values", getListIntoInteger());
			startActivity(i);
			break;
		case R.id.chainingprobe:
			i = new Intent("com.uco.compsci.HashActivity");
			// put in we want Quaker and the size
			i.putExtra("type", Probing.CHAINING_PROBING);
			i.putExtra("values", getListIntoInteger());
			startActivity(i);			
			break;

		}

	}

	private int[] getListIntoInteger() {
		int[] x = new int[listItems.size()];
		for(int y = 0; y < listItems.size(); y++){
			x[y] = Integer.parseInt(listItems.get(y));
		}
		return x;
	}

}
