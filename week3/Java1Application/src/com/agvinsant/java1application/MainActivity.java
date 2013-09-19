/*
 *  project Java1Application
 * 
 * package com.agvinsant.java1application
 * 
 * @author Adam Vinsant
 * 
 * date Sep 11, 2013
 * 
 */
package com.agvinsant.java1application;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	Context context;
	String[] songName;
	Resources res;
	TextView results;
	
	Boolean connected = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = this;
		res = getResources();
		
		// setting the linear layout
		LinearLayout ll = new LinearLayout(this);
		LinearLayout ml = BasicLayout.layoutWithButton(this, "Show Info");
		ll.setOrientation(LinearLayout.VERTICAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		// Creating button from BasicLayout class
		Button mb = (Button) ml.findViewById(1);
		
		
		
		
		
		// setting the content view
		setContentView(ll);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
