package com.uco.compsci;

import DrawViews.AVLDrawView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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
		
		AVLdv = new AVLDrawView(this, getWindowManager().getDefaultDisplay());
		AVLdv.setId(1000);
		createRelative();
		
		setContentView(rl);
		AVLdv.start();
	}
	
	//onDestory is called when activity is being destroyed by the system
	//very important to override this because of our extra thread
	@Override
	protected void onDestroy() {
		//stops the thread
		AVLdv.killThread();
		super.onDestroy();
	}
	
	
	public void createRelative() {
		rl = new RelativeLayout(this);
		rl.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE); 
		//params.addRule(RelativeLayout.a, RelativeLayout.TRUE); 		
		
		Button b = new Button(this);
		b.setLayoutParams(params);
		b.setText("Next");
		b.setId(2000);
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				AVLdv.buttonChangePause();
			}
		});
		rl.addView(b);
		rl.addView(AVLdv);
		
		TextView tv = new TextView(this);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.ABOVE, 2000);
	
		tv.setGravity(Gravity.BOTTOM);
		tv.setLayoutParams(params);
		tv.setTextSize(20);
		tv.setTextColor(Color.RED);
		AVLdv.setTextView(tv);
		rl.addView(tv);
		
	}
}