package com.profbingo.android;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProfBingo extends Activity implements OnSharedPreferenceChangeListener {

	protected SharedPreferences defaultSharedPreferences;
	protected String authKey = "";
	protected String passwordHash = "";

	public static final int LOGIN_DIALOG = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupPreferences();
		setupButtons();

	}

	private void setupButtons() {
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {	
				// Login Call
				login();
			}
		});

	}

	private void setupPreferences() {
		// Load Preferences
		defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);

	}

	public void onSharedPreferenceChanged(SharedPreferences prefs, String arg1) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inf = this.getMenuInflater();
		inf.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {

		case R.id.prefs_menu_item:
			Intent prefsIntent = new Intent(this, ProfBingoPrefs.class);
			startActivity(prefsIntent);
			break;
		}
		return true;
	}
	
	
	
	private boolean login(){
		//Validate the user credentials...
		String user = defaultSharedPreferences.getString(getString(R.string.key_email_prefs),"DEFAULT_USER");
		String pass = defaultSharedPreferences.getString(getString(R.string.key_pass_prefs),"DEFAULT_PASS");
		//Build Map for conversion to JSON

	
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("username", user);
		map.put("pwhash", NetUtil.hashStringSHA(pass + user));
		// Post HTTP Call.
		String result = NetUtil.postJsonData(map, "http://profbingo.heroku.com/login");
		Log.d("PB", "HTTP login post returned: " + result);
	
		return false;
		
	}



}