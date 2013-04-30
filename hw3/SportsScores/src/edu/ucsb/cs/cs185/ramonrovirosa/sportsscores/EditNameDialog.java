
package edu.ucsb.cs.cs185.ramonrovirosa.sportsscores;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class EditNameDialog extends DialogFragment implements OnEditorActionListener {

    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    private EditText Team1Name,Score1,Team2Name,Score2;

    public EditNameDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        Team1Name = (EditText) view.findViewById(R.id.txt_team_name);
        Score1 = (EditText) view.findViewById(R.id.score1_txt_frag);
        Team2Name = (EditText) view.findViewById(R.id.team2_txt);
        Score2 = (EditText) view.findViewById(R.id.score2_txt_frag);
        getDialog().setTitle("Enter Game");
        
       

        

        Button b = (Button)view.findViewById(R.id.done_Frag);
        b.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		//Team1Name.getText().toString();
        		
        		TextView team1 = (TextView) getActivity().findViewById(R.id.team1);
        		team1.setText(Team1Name.getText().toString());
        		
        		TextView score1 = (TextView) getActivity().findViewById(R.id.score1);
        		score1.setText(Score1.getText().toString());
        		
        		TextView team2 = (TextView) getActivity().findViewById(R.id.team2);
        		team2.setText(Team2Name.getText().toString());
        		
        		TextView score2 = (TextView) getActivity().findViewById(R.id.score2);
        		score2.setText(Score2.getText().toString());
        		
        		getDialog().dismiss();
        	}
        	
        });
        
        // Show soft keyboard automatically
//        Team1Name.requestFocus();
//        getDialog().getWindow().setSoftInputMode(
//                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        Team1Name.setOnEditorActionListener(this);
        
        AutoCompleteTextView textView = (AutoCompleteTextView) view.findViewById(R.id.txt_team_name);
        String[] teamsArray = getResources().getStringArray(R.array.teamsArray);
        ArrayAdapter<String> adapter=
        		new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, teamsArray);
        textView.setAdapter(adapter);
        
        AutoCompleteTextView textView2 = (AutoCompleteTextView) view.findViewById(R.id.team2_txt);
        String[] teamsArray2 = getResources().getStringArray(R.array.teamsArray);
        ArrayAdapter<String> adapter2=
        		new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, teamsArray2);
        textView2.setAdapter(adapter2);
        
        
        return view;
    }



    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    	if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(Team1Name.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
   
}
