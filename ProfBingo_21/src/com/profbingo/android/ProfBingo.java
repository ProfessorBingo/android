package com.profbingo.android;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.opengl.Visibility;
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

	public static final int LOGIN_DIALOG = 1;

	protected SharedPreferences defaultSharedPreferences;
	protected String authKey = "";
	protected String passwordHash = "";
	
	
	
	protected Button logInOutButton;
	protected boolean loggedIn = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupPreferences();
		setupButtons();

	}

	private void setupButtons() {
		 logInOutButton = (Button) findViewById(R.id.loginout_button);
		 logInOutButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Login Call
				if(loggedIn)
					logout();
				else
					login();
			}
		});
		 
		 
		 Button prefButton = (Button) findViewById(R.id.prefs_button);
		 prefButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startPrefsActivity();
				
			}
		});

	}

	private void setupPreferences() {
		// Load Preferences
		defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);

	}

	@Override
	protected void onResume() {
		super.onResume();

		updateDisplay();

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
			startPrefsActivity();
			break;
		}
		return true;
	}

	private void startPrefsActivity() {
		Intent prefsIntent = new Intent(this, ProfBingoPrefs.class);
		startActivity(prefsIntent);
	}

	private boolean login() {
		// Create Dialog
		ProgressDialog pd = ProgressDialog.show(ProfBingo.this, "Logging in", "", true);

		// Validate the user credentials...
		String user = defaultSharedPreferences.getString(getString(R.string.key_email_prefs), "DEFAULT_USER");
		String pass = defaultSharedPreferences.getString(getString(R.string.key_pass_prefs), "DEFAULT_PASS");
		// Build Map for conversion to JSON

		// Log In
		String authCode = NetUtil.logIn(user, pass);
		if (authCode.equals("")) {
			loggedIn = false;
			Log.d("PB", "Login failed");
		}
		else {
			
			Editor e = defaultSharedPreferences.edit();
			e.putString("authcode", authCode);
			e.commit();
			loggedIn = true;
			Log.d("PB", "Logged in as:" +  user);
			
			
			
		}
		updateDisplay();
		pd.dismiss();

		return loggedIn;

	}

	private boolean logout(){
		ProgressDialog pd = ProgressDialog.show(ProfBingo.this, "Logging out", "", true);
		boolean result = false;
		String authcode = defaultSharedPreferences.getString("authcode", "");
		
		if(!authcode.equals(""))
			 result =  NetUtil.logOut(authcode);
		
		
		
		if(result){
			loggedIn = false;
			Editor e = defaultSharedPreferences.edit();
			e.putString("authcode", "");
			e.commit();
			
		}
		updateDisplay();
		
		pd.dismiss();
		return result;
	}
	
	private void updateDisplay() {
		if (loggedIn) {
			logInOutButton.setText(R.string.logout);
		} else {
			logInOutButton.setText(R.string.login);
		}
		
		String user = defaultSharedPreferences.getString(getString(R.string.key_email_prefs), "DEFAULT_USER");
		if(user.equals("") || user.equals("DEFAULT_USER"))
			logInOutButton.setVisibility(View.INVISIBLE);
		else
			logInOutButton.setVisibility(View.VISIBLE);
		
	}
	

	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		updateDisplay();
		
	}

}