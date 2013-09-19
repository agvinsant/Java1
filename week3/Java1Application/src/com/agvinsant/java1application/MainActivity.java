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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.agvinsant.lib.WebClass;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	Context context;
	String[] songName;
	Resources res;
	TextView results;
	Spinner viewSpinner;
	TextView jsonView;
	TextView connectedView;
	String trackName;
	String artistName;
	String albumName;
	String trackSite;

	ArrayList<String> trackNameList = new ArrayList<String>();
	ArrayList<String> artistNameList = new ArrayList<String>();
	ArrayList<String> albumNameList = new ArrayList<String>();
	ArrayList<String> trackSiteList = new ArrayList<String>();
	
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
		mb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*Get selected team info.*/
				int pos = viewSpinner.getSelectedItemPosition();
				String tName = trackNameList.get(pos).toString();
				String arName = artistNameList.get(pos).toString();
				String alName = albumNameList.get(pos).toString();  
				String tSite = trackSiteList.get(pos).toString();

				jsonView.setText("Song Name: " +tName+ "\r\n" + "Artist Name: " +arName+ "\r\n" +"Album Name: "+alName+ "\r\n" + "Song Website: " +tSite);
			}

		});
		
		// calling the getSongInfo function 
		getSongInfo();
		
		connectedView = new TextView(context);
		
		// Detecting network settings
		connected = WebClass.getConnectionStatus(context);
		if(connected){
			
			connectedView.setText("Network Connection: " + WebClass.getConnectionType(context)+"\n");
		}
		else{
				connectedView.setText(" "+WebClass.getConnectionType(context)+"\n");
		}

				

		
		// song length display
		int songNum = res.getStringArray(R.array.songArray).length;
		TextView tv = new TextView(context);
		tv.setText("Check out one of the "+songNum+"songs on the album");
		
		//spinner adapter
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, songName);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//creating the spinner
		viewSpinner = new Spinner(context);
		viewSpinner.setAdapter(spinnerAdapter);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		viewSpinner.setLayoutParams(lp);
		
		//spinner onClick function
		viewSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(context, "You selected " + songName[position], Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		
		// setting different layout parts to the main layout
		ll.addView(ml);
		ll.addView(tv);
		ll.addView(viewSpinner);
		ll.addView(connectedView);
		ll.addView(jsonView);
		
		
		// setting the content view
		setContentView(ll);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//get URL
	private void getSongInfo(){
		String baseURL = "https://itunes.apple.com/search?term=groove+logic+logical+thinking";
		URL finalURL;
		try{
			finalURL = new URL(baseURL);
			songRequest sr = new songRequest();
			sr.execute(finalURL);
		} catch (MalformedURLException e){
			Log.e("BAD URL", "MALFORMED URL");
			finalURL = null;
		}
	}
	
	//get data from URL
	private class songRequest extends AsyncTask<URL, Void, String>{
		@Override
		protected String doInBackground(URL... urls){
			String response = "";
			for(URL url: urls){
				response = WebClass.getURLStringResponse(url);
			}
			return response;
		}
		//get data and add to arrays.
		@Override
		protected void onPostExecute(String result){
			try {
				JSONArray jsonArray = new JSONArray(result);
	
				int n = jsonArray.length();
				for(int i = 0;i<n; i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
	
					trackName = jsonObject.getString("trackName");
					artistName= jsonObject.getString("artistName");
					albumName = jsonObject.getString("collectionName");
					trackSite= jsonObject.getString("trackViewUrl");
					trackNameList.add(trackName);
					artistNameList.add(artistName);
					albumNameList.add(albumName);  
					trackSiteList.add(trackSite);
				}
	
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}

}


