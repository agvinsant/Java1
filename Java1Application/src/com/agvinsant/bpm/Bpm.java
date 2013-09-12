/*
 *  project Java1Application
 * 
 * package com.agvinsant.bpm
 * 
 * @author Adam Vinsant
 * 
 * date Sep 11, 2013
 * 
 */

// This is the enum file that feeds data to the JSON and the MainActivity. 

package com.agvinsant.bpm;

public enum Bpm {
	
	// This is the list of genres that includes the type and average beats per minute or BPM. This is the speed of the song, real important for DJ's and producers
	HOUSE("House", "128"),
	TRANCE("Trance", "130"),
	JUNGLE("Jungle", "174"),
	DUBSTEP("Dubstep", "140");
	
	private final String genreType;
	private final String bpmValue;
	
	// sets the genre type and bpm. 
	private Bpm(String genreType, String bpmValue) {
		this.genreType = genreType;
		this.bpmValue = bpmValue;
	}
	// sets the genreType data object
	public String setGenreType(){
		
		return genreType;
	}
	// sets the bpm data object
	public String setBpmValue(){
		
		return bpmValue;
	}
}
