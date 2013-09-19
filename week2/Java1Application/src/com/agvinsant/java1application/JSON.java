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

// This is the JSON data file for the application. It creates data objects and stores values for the application.


package com.agvinsant.java1application;

import org.json.JSONException;
import org.json.JSONObject;

import com.agvinsant.bpm.Bpm;

public class JSON {
	// Builds the JSON and returns the JSON object
	
	public static JSONObject buildJSON(){
		
		// creating the genre object
		JSONObject genreObject = new JSONObject();
		
	try {
		
		// creating the query object
				JSONObject queryObject = new JSONObject();
				
				for (Bpm bpm : Bpm.values()){
					
					JSONObject infoObject = new JSONObject();
					
					infoObject.put("genreType", bpm.setGenreType());
					infoObject.put("bpmValue", bpm.setBpmValue());
					queryObject.put(bpm.name().toString(), infoObject);
				}
		
	} catch (JSONException e) {
		e.printStackTrace();
	}	
		
		
		return genreObject;
	}
	
	public static String readJSON(String selected){
		
		String result, genreType, bpmValue;
		JSONObject object = buildJSON();
		
		try {
			genreType = object.getJSONObject("query").getJSONObject(selected).getString("genreType");
			bpmValue = object.getJSONObject("query").getJSONObject(selected).getString("bpmValue");
			
			result = "Genre Type: " + genreType + "\r\n" +
						"Average Beat Per Minute: " + bpmValue + "\r\n";
			
		} catch (JSONException e) {
			
			e.printStackTrace();
			result = e.toString();
		}
		
		return result;
		
	}
	
	
}
