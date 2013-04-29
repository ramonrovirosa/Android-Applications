package edu.ucsb.cs.cs185.ramonrovirosa.colormeprogrammatically;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView tv = new TextView(this);
//        tv.setText("Hello, Android");
//        setContentView(tv);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
       
        
        
        Button redButton = new Button(this);
        Button greenButton = new Button(this);
        Button blueButton = new Button(this);
        Button yellowButton = new Button(this);
        
        redButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        greenButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        blueButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        yellowButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
        
        redButton.setText("Red");
        greenButton.setText("Green");
        blueButton.setText("Blue");
        yellowButton.setText("Yellow");
        
       
        
        layout.addView(redButton);
        layout.addView(greenButton);
        layout.addView(blueButton);
        layout.addView(yellowButton);

        setContentView(layout);
        
        redButton.setOnClickListener(new OnClickListener() {
        	LinearLayout vi;
            @Override
            public void onClick(View view) {
                // do whatever stuff you wanna do here
            	vi = (LinearLayout)view.getParent();
            	vi.setBackgroundColor(getResources().getColor(R.color.red_color));
            }
        });
        
        greenButton.setOnClickListener(new OnClickListener() {
        	LinearLayout vi;
            @Override
            public void onClick(View view) {
                // do whatever stuff you wanna do here
            	vi = (LinearLayout)view.getParent();
            	vi.setBackgroundColor(getResources().getColor(R.color.green_color));
            }
        });
        
        blueButton.setOnClickListener(new OnClickListener() {
        	LinearLayout vi;
            @Override
            public void onClick(View view) {
                // do whatever stuff you wanna do here
            	vi = (LinearLayout)view.getParent();
            	vi.setBackgroundColor(getResources().getColor(R.color.blue_color));
            }
        });
        
        yellowButton.setOnClickListener(new OnClickListener() {
        	LinearLayout vi;
            @Override
            public void onClick(View view) {
                // do whatever stuff you wanna do here
            	vi = (LinearLayout)view.getParent();
            	vi.setBackgroundColor(getResources().getColor(R.color.yellow_color));
            }
        });
        
    }
    
    

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
    
}
