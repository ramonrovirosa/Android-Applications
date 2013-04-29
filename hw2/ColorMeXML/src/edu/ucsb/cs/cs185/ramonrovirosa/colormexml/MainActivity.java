package edu.ucsb.cs.cs185.ramonrovirosa.colormexml;

import edu.ucsb.cs.cs185.ramonrovirosa.colormexml.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "edu.ucsb.cs.cs185.ramonrovirosa.colormexml.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    	
    	LinearLayout vi;
    	
        if(view.getTag().toString().equals("redButton")){
        	vi = (LinearLayout)view.getParent();
        	vi.setBackgroundColor(getResources().getColor(R.color.red_color));
        }
        
        if(view.getTag().toString().equals("greenButton")){
        	vi = (LinearLayout)view.getParent();
        	vi.setBackgroundColor(getResources().getColor(R.color.green_color));
        }
        if(view.getTag().toString().equals("blueButton")){
        	vi = (LinearLayout)view.getParent();
        	vi.setBackgroundColor(getResources().getColor(R.color.blue_color));
        }
        if(view.getTag().toString().equals("yellowButton")){
        	vi = (LinearLayout)view.getParent();
        	vi.setBackgroundColor(getResources().getColor(R.color.yellow_color));
        }
        
        
    	
    	
    }
    
}
