package edu.ucsb.cs.cs185.hw4skeleton;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class KeepScoreList extends Activity {
	ArrayList<String> data;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainactivity);
		
		
		
		if(getIntent().getStringArrayListExtra("scoreList") != null){
			//Intent intename = getIntent();
			data = getIntent().getStringArrayListExtra("scoreList");
			

		}
		else{
			data = new ArrayList<String>();
			
		}
		
		ListView lv = (ListView) findViewById(R.id.mainListView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		lv.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mainactivity, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.ScoreList);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.NewEntry:
			newEntryItem();
			break;
		case R.id.Settings:
			settingMenuItem();
			break;
		case R.id.Help:
			helpMenuItem();
			break;

		}

		return true;
	}

	public void settingMenuItem() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		// builder.setIcon(R.drawable.dialog_question);
		builder.setTitle(R.string.settings);

		TextView tv = new TextView(this);
		tv.setText(R.string.settingsMessage);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setPadding(0, 25, 0, 0);
		builder.setView(tv);

		AlertDialog alert = builder.create();
		alert.show();
	}

	public void helpMenuItem() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		// builder.setIcon(R.drawable.dialog_question);
		builder.setTitle(R.string.settings);

		TextView tv = new TextView(this);
		tv.setText(R.string.helpMessage);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setPadding(0, 15, 0, 0);
		// builder.setView(tv);

		AlertDialog alert = builder.create();
		alert.setView(tv);
		alert.show();
	}

	public void newEntryItem() {
		Intent intObj = new Intent(KeepScoreList.this, KeepScoreActivity.class);
		ArrayList<String> scoreList = data;
		intObj.putExtra("scoreList", scoreList);
		startActivity(intObj);
		finish();

	}
}
