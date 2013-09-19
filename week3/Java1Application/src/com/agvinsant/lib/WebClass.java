package com.agvinsant.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WebClass {
	
	private static void webInfo(Context context){
		
		Boolean connection = false;
		String connectionType = "Unavailable";
		
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if(ni != null){
			
			if(ni.isConnected()){
				connectionType = ni.getTypeName();
				connection = true;
				
			}
		}
	}
}
