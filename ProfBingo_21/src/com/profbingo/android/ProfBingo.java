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
		Button login = (Button) findViewById(R.id.login_button);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Login Call
				if (login()) {
					loggedIn = true;
				} else {
					loggedIn = false;
				}
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

		if (loggedIn) {

		} else {

		}

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

	private boolean login() {
		// Create Dialog
		ProgressDialog pd = ProgressDialog.show(ProfBingo.this, "Logging in", "", true);

		// Validate the user credentials...
		String user = defaultSharedPreferences.getString(getString(R.string.key_email_prefs), "DEFAULT_USER");
		String pass = defaultSharedPreferences.getString(getString(R.string.key_pass_prefs), "DEFAULT_PASS");
		// Build Map for conversion to JSON

		// Log In
		String authCode = NetUtil.logIn(user, pass);
		if (!authCode.equals("")) {
			Editor e = defaultSharedPreferences.edit();
			e.putString("authcode", authCode);
			e.commit();
		}

		pd.dismiss();

		return false;

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog d;
		switch (id) {
		case (LOGIN_DIALOG):
			ProgressDialog progressDialog = new ProgressDialog(this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Loading...");
			progressDialog.setCancelable(true);
			d = progressDialog;
			break;

		}
		return super.onCreateDialog(id);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		super.onPrepareDialog(id, dialog);
	}

	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}