package com.agvinsant.java1application;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// setting the main layout view
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		//setting the params
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		// creating and setting the top text line
		TextView topLine = new TextView(this);
		topLine.setText("EDM Genre Information");
		ll.addView(topLine);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
