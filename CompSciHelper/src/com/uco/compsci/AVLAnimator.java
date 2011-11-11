package com.uco.compsci;

import DrawViews.AVLDrawView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;

public class AVLAnimator extends Activity {
	AVLDrawView AVLdv;
	RelativeLayout rl;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AVLdv = new AVLDrawView(this, getWindowManager().getDefaultDisplay());
		createRelative();
		rl.addView(AVLdv);
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
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				AVLdv.buttonChangePause();
			}
		});
		rl.addView(b);
	}
}