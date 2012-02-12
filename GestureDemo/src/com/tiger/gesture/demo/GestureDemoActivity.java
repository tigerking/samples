package com.tiger.gesture.demo;

import com.tiger.gesture.demo.R;

import java.util.ArrayList;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.Toast;

public class GestureDemoActivity extends Activity implements OnGesturePerformedListener{
	
	
	GestureLibrary mLibrary;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        GestureOverlayView gestures=(GestureOverlayView)findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(this);

        mLibrary=GestureLibraries.fromRawResource(this,R.raw.gestures);
        
        if(!mLibrary.load()) {
        	finish();
        }

    }
    
    
    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
    	// TODO Auto-generated method stub
    	ArrayList predictions =mLibrary.recognize(gesture);

    	//Wewantatleastoneprediction
    	if(predictions.size()>0){
    		Prediction prediction=(Prediction)predictions.get(0);
    		//Wewantatleastsomeconfidenceintheresult

    		if(prediction.score>1.0){
    			//Showthespell
    			Toast.makeText(this,prediction.name,Toast.LENGTH_SHORT).show();
    		}
    	}
    }

}