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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
	public static URL finalURL;

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
		
		// setting the content view from layout xml 
		setContentView(R.layout.form);
		// setting the results view from the layout xml
		jsonView = (TextView) findViewById(R.id.textView2);

		
		// Creating button from from layout xml
		Button mb = (Button) findViewById(R.id.button1);
		mb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Get selected song info
				int pos = viewSpinner.getSelectedItemPosition();
				String arName = artistNameList.get(pos).toString();
				String alName = albumNameList.get(pos).toString();  
				String tSite = trackSiteList.get(pos).toString();

	
				jsonView.setText("Artist Name: " +arName+ "\r\n" +"Album Name: "+alName+ "\r\n" + "Song Website: " +tSite);
			}

		});
		
		
		
		connectedView = (TextView) findViewById(R.id.textView1);
		
		
		//Detecting network settings
				connected = WebClass.getConnectionStatus(context);
				if(connected){
					Log.i("Network Connection", WebClass.getConnectionType(context));
					
					connectedView.setText("Network Connection: " + WebClass.getConnectionType(context)+"\n");
					
					// calling the getSongInfo function 
					getSongInfo();
				}
				else{
						connectedView.setText(""+WebClass.getConnectionType(context)+"\n");
				}

				
		
		// setting song array 
		songName = res.getStringArray(R.array.songArray);
		Log.i("songName", songName[0]);
		
		
		//spinner adapter
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, songName);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//creating the spinner
		viewSpinner = (Spinner) findViewById(R.id.spinner1);
		viewSpinner.setAdapter(spinnerAdapter);
		
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

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//get URL
	private void getSongInfo(){
		
		Log.i("getSongInfo", "hit function");
		
		String baseURL = "https://itunes.apple.com/search";
		
		try{
			finalURL = new URL(baseURL+"?term=groove+logic+logical+thinking");
			songRequest sr = new songRequest();
			sr.execute(finalURL);
			
			Log.i("getSongInfo", "hit function");
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
			
				response = WebClass.getURLStringResponse(finalURL);
				
				Log.i("songRequest", response);
				return response;
			
			
		}
		
		//get data and add to arrays.
		@Override
		protected void onPostExecute(String result){
			Log.i("URL RESPONSE", result);
			
	try {
					
					Log.i("TRYING JSON", "trying json");
					//JSONObject json = new JSONObject(result);
					//JSONObject results = jsonObject.getJSONObject("results");
					
					JSONObject mainJSON = new JSONObject(result);
		
					JSONArray jsonResult = mainJSON.getJSONArray("results");
						
					int n = jsonResult.length();
					for (int i = 0; i<n; i++ ){	
						
						JSONObject child = jsonResult.getJSONObject(i);
												
						artistName= child.getString("artistName");
						Log.i("artistName", artistName);
						albumName = child.getString("collectionName");
						Log.i("albumName", albumName);
						trackSite= child.getString("trackViewUrl");
						Log.i("trackSite", trackSite);
						trackNameList.add(trackName);
						artistNameList.add(artistName);
						albumNameList.add(albumName);  
						trackSiteList.add(trackSite);
					}
		
				} catch (JSONException e) {
					Log.e("JSONException", "ERROR", e);
					e.printStackTrace();
				}
		}	
	}

}


