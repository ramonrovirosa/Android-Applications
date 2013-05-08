
package edu.ucsb.cs.cs185.ramonrovirosa.sportsscores;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.ucsb.cs.cs185.ramonrovirosa.sportsscores.EditNameDialog.EditNameDialogListener;
//import android.app.DialogFragment;

public class SportsScores extends FragmentActivity implements EditNameDialogListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    View theView;
    public void sendMessage(View view) {
        // Do something in response to button
    	
    	LinearLayout vi;
    	theView = view;
    	
        if(view.getTag().toString().equals("enter_date"))
        	showDatePickerDialog(view);
        if(view.getTag().toString().equals("enter_game"))
        	showEditDialog(view);
        if(view.getTag().toString().equals("next_game"))
        	NextGame(view);
        	
        	
    }

   
    public void NextGame(View v) {
    	TextView text1 = (TextView) findViewById(R.id.dateView);
    	text1.setText("Date");
    
    	TextView text2 = (TextView) findViewById(R.id.team1);
    	text2.setText("Team 1");
    	
    	TextView text3 = (TextView) findViewById(R.id.score1);
    	text3.setText("0");
    	
    	TextView text4 = (TextView) findViewById(R.id.team2);
    	text4.setText("Team 2");
    	
    	TextView text5 = (TextView) findViewById(R.id.score2);
    	text5.setText("0");
    	
    	
     
    }
    
    private void showEditDialog(View v) {
        FragmentManager fm = getSupportFragmentManager();
        EditNameDialog editNameDialog = new EditNameDialog();
        editNameDialog.show(fm, "dlg_edit_name");
      

        
     
    }
    
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
     
    }
	
    @Override
    public void onFinishEditDialog(String inputText) {
        //Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }
    
	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			//final Calendar c = Calendar.getInstance();
			int year = 2013;
			int month = 03;
			int day = 17;

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			TextView text = (TextView) getActivity().findViewById(R.id.dateView);
			String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
			text.setText(months[month] + " " + day + ", " + year);
			
		}
		
	}

		    
		    

}
