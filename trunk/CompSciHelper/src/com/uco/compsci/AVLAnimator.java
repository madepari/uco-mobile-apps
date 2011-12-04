package com.uco.compsci;

import DrawViews.AVLDrawView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AVLAnimator extends Activity {
	AVLDrawView AVLdv;
	RelativeLayout rl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String type = getIntent().getExtras().getString("type");
		if (type.equals("simple_single_rotation")) {
			type = AVLDrawView.SIMPLE_SINGLE_ROTATION;
		} else if (type.equals("single_rotation")) {
			type = AVLDrawView.SINGLE_ROTATION;
		} else if (type.equals("double_rotation")) {
			type = AVLDrawView.DOUBLE_ROTATION;
		}

		AVLdv = new AVLDrawView(this, getWindowManager().getDefaultDisplay(),
				type);
		AVLdv.setId(1000);
		createRelative();

		setContentView(rl);
		AVLdv.start();
	}

	// onDestory is called when activity is being destroyed by the system
	// very important to override this because of our extra thread
	@Override
	protected void onDestroy() {
		// stops the thread
		AVLdv.killThread();
		Thread.yield();
		super.onDestroy();
	}

	public void createRelative() {
		rl = new RelativeLayout(this);
		rl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		// params.addRule(RelativeLayout.a, RelativeLayout.TRUE);

		Button b = new Button(this);
		b.setLayoutParams(params);
		b.setText("Next");
		b.setId(2000);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (!AVLdv.isRunning())
					AVLdv.buttonChangePause();
			}
		});
		rl.addView(b);
		rl.addView(AVLdv);

		TextView tv = new TextView(this);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);

		Resources r = getResources();
		params.setMargins(0, (int) Math.rint(TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics())), 0, 0);
		tv.setLayoutParams(params);
		tv.setTextSize(18);
		tv.setTextColor(Color.RED);
		AVLdv.setTextView(tv);
		rl.addView(tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater(); // from activity
		inflater.inflate(R.menu.avlmenu, menu);
		// It is important to return true to see the menu
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.moreinfo) {
			Intent browserIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://en.wikipedia.org/wiki/Karnaugh_map"));
			startActivity(browserIntent);
		}

		return true;
	}
}