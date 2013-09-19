package com.agvinsant.java1application;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BasicLayout {
	
			// creating the linear layout
			public static LinearLayout layoutWithButton(Context context, String buttonTxt){
				
				// setting the linear layout
				LinearLayout ll = new LinearLayout(context);
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
				ll.setLayoutParams(lp);
				
				// creating and setting the top text line
				TextView topLine = new TextView(context);
				topLine.setText("Groove Logic Logical Thinking EP");
				ll.addView(topLine);
				
				
				//Main button created
				Button button = new Button(context);
				button.setText(buttonTxt);
				button.setId(1);
				ll.addView(button);
				
				return ll;
			}	
}
