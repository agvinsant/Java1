package com.agvinsant.java1application;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	LinearLayout ll;
	String[] genreList;
	TextView genreInfo;
	Resources res = getResources();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// setting the main layout view
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		//setting the params
		LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		// creating and setting the top text line
		TextView topLine = new TextView(this);
		topLine.setText("EDM Genre Information");
		ll.addView(topLine);
		
		// creating some space betwen text views
		TextView blank  = new TextView(this);
		blank.setText("");
		ll.addView(blank);
		
		// creating the instruction line
		TextView instructions = new TextView(this);
		instructions.setText("Pick a genre from the list below to see information about this genre");
		ll.addView(instructions);
		
		// creating an array adapter for the listview
		
		genreList = res.getStringArray(R.array.genreArray);
		
		// listView adapter
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, genreList);
		
		// creating the list
		ListView list = new ListView(this);
		list.setAdapter(listAdapter);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		list.setLayoutParams(lp);
		ll.addView(list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
					// setting integer variables
					int house = res.getInteger(R.integer.house);
					int trance = res.getInteger(R.integer.trance);
					int jungle = res.getInteger(R.integer.jungle);
					int dubstep = res.getInteger(R.integer.dubstep);
				
					String listText = genreList[position].toString();
					
				
			}
		});
		
		// genre results
		genreInfo = new TextView(this);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		genreInfo.setLayoutParams(lp);
		ll.addView(genreInfo);
		
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
